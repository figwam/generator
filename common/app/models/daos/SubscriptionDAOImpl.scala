package models.daos

import java.util.UUID
import javax.inject.Inject

import models._
import play.api.db.slick.DatabaseConfigProvider
import play.libs.Json

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import utils.Utils._
trait SubscriptionDAO  {

  //def listByTrainee(idTrainee: UUID): Future[List[String]]

}

class SubscriptionDAOImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends SubscriptionDAO with DAOSlick {
  import driver.api._

  /*
   def listByTrainee(): Future[List[String]] = {


        val query = for {
          subs <- slickSubscriptions
            .join(slickOffers).on(_.idOffer === _.id)
            //.joinLeft(slickTimeStops).on(_._1.id === _.idSubscription)
            //.groupBy(_.isActive)
        } yield (subs)

        db.run(query.result).map { result =>
          result.map {
            case (s) => {
              println("--->"+s)
              /*val sub = Subscription(s._1.id,s._1.isActive,asCalendar(s._1.createdOn),None,
                Offer(s._2.id, s._2.name, s._2.nrAccess, s._2.nrAccessSame, s._2.price, asDatetime(s._2.createdOn)),
                Some(List(Timestop(t._1.id,asCalendar(t._1.stopOn), t._1.reason, asCalendar(t._1.createdOn)))))

              println("--->"+sub.id)*/
            }
              //print("-SU->"+s._1.id); println("-TS->"+ts._1.id+"---"+ts._1.idSubscription);
          }
        }
     Future(List("aaa"))




        val action = (for {
          result <- query
        } yield {
            booksResult.map { row =>
              val (libraryTableRow, libraryToBooksTableRow) = row._1
              val booksTableRow = row._2
              // TODO: Access all data from the rows and construct desired DS
            }
          }

        val m = Map(Long, Int)
    m.++()
        m.+(Tuple2(1L,2))

        val query = for {
          dbSubscription <- slickSubscriptions.filter(_.isActive === true)
          dbTimestop <- slickTimeStops.filter(_.idSubscription === dbSubscription.id)
          dbOffer <- slickOffers.filter(_.id === dbSubscription.idOffer)
        } yield (dbSubscription, dbTimestop, dbOffer)
        db.run(query.result).map { result =>
          result.toList.map {
            case (s, t, offer) =>
              m + "a"
                Subscription(s.id,s.isActive,asCalendar(s.createdOn),s.canceledOn match {case t => Some(asCalendar(t.get)) case _ => None},
                  Offer(offer.id, offer.name, offer.nrAccess, offer.nrAccessSame, offer.price),
                  Some(List(Timestop(t.id,asCalendar(t.stopOn), t.reason, asCalendar(t.createdOn)))))

          }
        }



  } */
}
