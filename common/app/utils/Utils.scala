package utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.{GregorianCalendar, UUID, Calendar}

import models.Recurrence.Recurrence
import models.{Bill, Recurrence, Clazz, ClazzDefinition}
import play.Logger
import play.api.libs.json.{JsString, JsSuccess, JsValue, Format}

import scala.collection.mutable.ListBuffer

/**
 * Created by alex on 01/10/15.
 */
object Utils {

  object Implicits {

    implicit object calendarFormat extends Format[Calendar] {
      val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")
      def reads(json: JsValue) = {
        val str = json.as[String]
        val gc = new GregorianCalendar
        gc.setTimeInMillis(format.parse(str).getTime)
        JsSuccess(gc)
      }
      def writes(c: Calendar) = JsString(format.format(c.getTime))
    }


    implicit object recurrenceFormat extends Format[Recurrence] {
      def reads(json: JsValue) = {
        val str = json.as[String]
        JsSuccess(Recurrence.withName(str))
      }
      def writes(r: Recurrence) = JsString(r+"")
    }
  }


  final def asCalendar (ts: Timestamp): Calendar = {val c = new GregorianCalendar(); c.setTime(ts);c}
  final def asTimestamp (c: Calendar): Timestamp = new Timestamp(c.getTimeInMillis)

  /**
   * This method calculates the next classes from class definition. Class definition are usually
   * configured by partners and can be recurrent and onetime. In both cases the calculator will go
   * through the class definitions and generate next classes accordingly to their recurrence.
   *
   * @param clazzDef The man class definition created by partner
   * @param seeInAdvanceDays The amount of days to calculate the classes in advance
   * @return The list of classes which can e.g be created in the database
   */
  final def calculateNextClazzes(clazzDef: ClazzDefinition, seeInAdvanceDays: Int) : List[Clazz] = {
    /*Transform to gregorian calendar, for easier calculation*/
    val calculateTill = new GregorianCalendar();calculateTill.add(Calendar.DAY_OF_YEAR, seeInAdvanceDays)
    val now = new GregorianCalendar()

    def addDays(clazz: Clazz, daysToAdd: Int) : Clazz = {
      clazz.startFrom.add(Calendar.DAY_OF_YEAR, daysToAdd)
      clazz.endAt.add(Calendar.DAY_OF_YEAR, daysToAdd)
      clazz
    }


    val clazz  = Clazz(None, clazzDef.startFrom,clazzDef.endAt, clazzDef.name, clazzDef.contingent, Some(clazzDef.avatarurl), clazzDef.description, clazzDef.tags, 0, "",clazzDef.id.get, null, None)
    List(
    // activ bis muss in zukunft liegen ODER
    // activ von muss vor calculateTill
        clazzDef.recurrence match {
          case (Recurrence.WEEKLY) => {
            if (clazzDef.activeTill.after(now) && clazzDef.activeFrom.before(calculateTill)) {
              var clazzes = new ListBuffer[Clazz]
              while (clazz.startFrom.before(now)) addDays(clazz, 7)
              while (clazz.startFrom.after(now)
                && clazz.startFrom.before(clazzDef.activeTill)
                && clazz.startFrom.before(calculateTill)
                && clazz.startFrom.before(calculateTill)) {
                val startFrom = new GregorianCalendar()
                startFrom.setTime(clazz.startFrom.getTime)
                val endAt = new GregorianCalendar()
                endAt.setTime(clazz.endAt.getTime)
                // add only if the startFrom is after activeFrom -> means its active
                if (clazzDef.startFrom.after(clazzDef.activeFrom)) clazzes += clazz.copy(startFrom=startFrom,endAt=endAt)
                addDays(clazz, 7)
              }
              clazzes.toList
            } else List()
          }
          case (Recurrence.ONETIME) => {
            if (clazzDef.startFrom.after(now) && clazzDef.startFrom.before(calculateTill)) List(clazz) else List()
          }
          case _ => Logger.warn("Unknown Recurrence type in clazz definition with id=" + clazzDef.id); List()
        }
    ).flatten
  }


  //final def calculateNextBills() : List[Bill] = {

  //}



  }
