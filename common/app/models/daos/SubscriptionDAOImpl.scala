package models.daos

import java.sql.Timestamp
import java.util.{GregorianCalendar, Calendar, UUID}
import javax.inject.Inject

import models.{Offer, Subscription, ClazzDefinition, Clazz}
import play.Logger
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import utils.Utils._
trait SubscriptionDAO  {

  def list(): Future[List[Subscription]]

}

class SubscriptionDAOImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends SubscriptionDAO with DAOSlick {
  import driver.api._

  override def list(): Future[List[Subscription]] = {
    /*
        val query = slickOffers
          .join(slickSubscriptions).on(_.id === _.idOffer)
          .join(slickTimeStops).on(_._2.id === _.idSubscription)
          .result

        val action = (for {
          result <- query
        } yield {
            booksResult.map { row =>
              val (libraryTableRow, libraryToBooksTableRow) = row._1
              val booksTableRow = row._2
              // TODO: Access all data from the rows and construct desired DS
            }
          }

        val query = for {
          dbSubscription <- slickSubscriptions.filter(_.isActive === true)
          dbTimestop <- slickTimeStops.filter(_.idSubscription === dbSubscription.id)
          dbOffer <- slickOffers.filter(_.id === dbSubscription.idOffer)
        } yield (dbSubscription, dbTimestop, dbOffer)
        db.run(query.result).map { result =>
          result.toList.map {
            case (s, t, offer) =>
              Subscription(s.id,s.isActive,asCalendar(s.createdOn),s.canceledOn match {case t => Some(asCalendar(t.get)) case _ => None},
                Offer(offer.id, offer.name, offer.nrAccess, offer.nrAccessSame, offer.price),
                t.)
          }
        }
    */

  }
}
