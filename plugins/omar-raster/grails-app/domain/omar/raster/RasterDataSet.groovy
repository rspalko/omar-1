package omar.raster

import omar.core.Repository

class RasterDataSet
{
  static hasMany = [fileObjects: RasterFile, rasterEntries: RasterEntry]
  Collection fileObjects
  Collection rasterEntries

  Repository repository

  static constraints = {
    repository( nullable: true )
  }
  static mapping = {
    cache true
    id generator: 'identity'
    repository index: 'raster_data_set_repository_idx'
  }

  static RasterDataSet initRasterDataSet(rasterDataSetNode, rasterDataSet = null)
  {
    rasterDataSet = rasterDataSet ?: new RasterDataSet()

    for ( def rasterFileNode in rasterDataSetNode.fileObjects.RasterFile )
    {
      RasterFile rasterFile = RasterFile.initRasterFile( rasterFileNode )
      rasterDataSet.addToFileObjects( rasterFile )
    }

    for ( def rasterEntryNode in rasterDataSetNode.rasterEntries.RasterEntry )
    {
      RasterEntry rasterEntry = new RasterEntry()
      rasterEntry.rasterDataSet = rasterDataSet
      RasterEntry.initRasterEntry( rasterEntryNode, rasterEntry )

      if ( rasterEntry.groundGeom )
      {
        rasterDataSet.addToRasterEntries( rasterEntry )
      }
    }

    return rasterDataSet
  }

  def getFileFromObjects(def type = "main")
  {
    return fileObjects?.find { it.type == type }
  }
}
