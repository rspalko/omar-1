package omar.geoscript

import geoscript.geom.Bounds
import geoscript.filter.Color
import geoscript.filter.Filter
import geoscript.render.Map as GeoScriptMap
import geoscript.style.Composite
import geoscript.workspace.Workspace
import static geoscript.style.Symbolizers.*

import grails.transaction.Transactional

import omar.core.ISO8601DateParser

// RP - colors not rendering correctly
import java.awt.RenderingHints
import org.geotools.renderer.lite.StreamingRenderer
import org.geotools.factory.Hints

@Transactional(readOnly=true)
class FootprintService
{
  def grailsApplication
  def geoscriptService

  def getFootprints(GetFootprintsRequest params)
  {
    def ostream = new ByteArrayOutputStream()
    def (prefix, layerName) = params.layers.split( ':' )
    def styles = grailsApplication.config.wms.styles

    def layerInfo = LayerInfo.where {
      name == layerName && workspaceInfo.namespaceInfo.prefix == prefix
    }.get()

    Workspace.withWorkspace( geoscriptService.getWorkspace( layerInfo.workspaceInfo.workspaceParams ) ) { workspace ->
      workspace.ds.setFetchSize(10000)

      def outlineLookupTable = styles[params.styles]

      def style = outlineLookupTable.collect { k, v ->
        ( stroke( color: new Color( v.color ) ) + fill( opacity: 0.0 ) ).where( v.filter )
      }

      // Not using file_type, so commenting this out
      //def x = outlineLookupTable.keySet().collect { "'${it}'" }.join( ',' )

      //style << ( stroke( color: '#000000' ) + fill( opacity: 0.0 ) ).where( "file_type not in (${x})" )

      def footprints = new QueryLayer( workspace[layerName], style as Composite )
      def viewBbox = new Bounds( *( params.bbox.split( ',' )*.toDouble() ), params.srs )
      def geomField = workspace[layerName].schema.geom
      def queryBbox

      if ( !workspace[layerName]?.proj?.equals( viewBbox?.proj ) )
      {
        queryBbox = viewBbox.reproject( workspace[layerName]?.proj )
      }
      else
      {
        queryBbox = viewBbox
      }

      def filter = Filter.intersects( geomField.name, queryBbox.geometry )

      if ( params.filter )
      {
        filter = filter.and( new Filter( params.filter ) )
      }

      // HACK - need to refactor this
      if ( params.time ) {
        def timePairs = ISO8601DateParser.parseOgcTimeStartEndPairs(params.time)

        timePairs?.each { timePair ->
          if ( timePair.start ) {
              filter = filter.and("acquisition_date >= ${timePair.start}")
          }
          if ( timePair.end ) {
              filter = filter.and("acquisition_date <= ${timePair.end}")
          }
        }
      }

      // println filter

      footprints.filter = filter

      def map = new GeoScriptMap(
          width: params.width,
          height: params.height,
          type: params.format.split( '/' ).last(),
          bounds: viewBbox,
          proj: viewBbox.proj,
          layers: [footprints]
      )
     
      map.renderer.setGeneralizationDistance(grailsApplication.config.footprintThreshold ?: 0.0)
      Rendering hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
      hints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED))
      hints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED))
      hints.add(new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR))
      hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED))
      map.renderer.setJava2DHints(hints)

      map.renderer.setRendererHints([(StreamingRenderer.SCALE_COMPUTATION_METHOD_KEY):StreamingRenderer.SCALE_OGC,(StreamingRenderer.LINE_WIDTH_OPTIMIZATION_KEY):Boolean.FALSE,(StreamingRenderer.ADVANCED_PROJECTION_HANDLING_KEY):false,(StreamingRenderer.CONTINUOUS_MAP_WRAPPING):false,(StreamingRenderer.OPTIMIZE_FTS_RENDERING_KEY):true])



      if ( map?.type?.equalsIgnoreCase('gif') ) {
        map.@renderers['gif'] = new TransparentGif()
      }

      map.render( ostream )
      map.close()
    }

    [contentType: params.format, buffer: ostream.toByteArray()]
  }
}
