package models

import java.util.{UUID, Calendar}

import play.api.libs.json._


case class Offer(    id: Option[UUID],
                     name: String,
                     nrAccess: Short,
                     nrAccessSame: Short,
                     price: scala.math.BigDecimal)


/**
 * The companion object.
 */
object Offer {


  import utils.Utils.Implicits._
  /**
   * Converts the [Bill] object to Json and vice versa.
   */
  implicit val jsonFormat = Json.format[Offer]
}