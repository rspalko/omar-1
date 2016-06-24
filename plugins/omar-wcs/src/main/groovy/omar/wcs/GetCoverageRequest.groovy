package omar.wcs

import grails.validation.Validateable
import groovy.transform.ToString

/**
 * Created by sbortman on 6/24/16.
 */
@ToString( includeNames = true )
class GetCoverageRequest implements Validateable
{
  static mapWith = 'none'

  String service
  String version
  String request
  String coverage
  String crs
  String bbox
  String format
  Integer width
  Integer height

  static mapping = {
    version false
  }
}