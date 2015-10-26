package models

import java.util.{UUID, Calendar}

import play.api.libs.json._


case class Subscription(
                         id: Option[UUID],
                         isActive: Boolean = true,
                         canceledOn: Option[Calendar] = None,
                         offer: Offer,
                         timestop: Option[Timestop] = None)


/**
 * The companion object.
 */
object Subscription {

  import utils.Utils.Implicits._
  /**
   * Converts the [Bill] object to Json and vice versa.
   */
  implicit val jsonFormat = Json.format[Subscription]
}