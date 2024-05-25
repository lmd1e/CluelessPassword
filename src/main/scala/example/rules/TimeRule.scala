package timerule

import rule._
import java.time.LocalTime

class TimeRule extends Rule{
  override def message(): String = "Your password must include the \u001B[4mcurrent time {HHMM}\u001B[24m."
  override def check(password: String): Boolean = {
    val currentTime = LocalTime.now()
    val time = currentTime.getHour * 100 + currentTime.getMinute
    password.contains(time.toString)
  }
}