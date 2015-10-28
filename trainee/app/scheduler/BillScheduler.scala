package scheduler

import java.util.UUID
import javax.inject.{Inject, Singleton}

import akka.actor.{Actor, Cancellable}
import models.Clazz
import models.daos.{ClazzDAO, ClazzDefinitionDAO}
import org.postgresql.util.PSQLException
import play.Play
import play.api.Logger
import play.libs.Json
import utils._

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by alex on 27/09/15.
 */
@Singleton
class BillScheduler @Inject() (clazzDAO: ClazzDAO, clazzDefinitionDAO: ClazzDefinitionDAO)  extends Actor {

  private val CREATE_BILLS = "CREATE_BILLS"

  private var scheduler: Cancellable = _

  override def preStart(): Unit = {
    import scala.concurrent.duration._
    scheduler = context.system.scheduler.schedule(
      initialDelay = 5.seconds,
      interval = 5.seconds,
      receiver = self,
      message = CREATE_BILLS
    )
  }



  override def postStop(): Unit = {
    scheduler.cancel()
  }

  def receive = {
    case CREATE_BILLS =>
      try {
        val clazzes =  clazzDefinitionDAO.listActive()
        Logger.debug("Execute Cron "+CREATE_BILLS+":"+Json.toJson(clazzes))

        }
        Logger.debug("Finished Cron "+CREATE_BILLS+":"+Json.toJson(clazzes))
        clazzes.onSuccess { case a => Logger("Classes created")/*case a => Logger.debug(s"Classes created: $a")*/ }
        clazzes.onFailure { case t: Throwable => Logger.error(t.getMessage,t) }
      } catch {
        case t: Throwable =>
          Logger.error(t.getMessage,t)
      }
  }

}