package currentdayofweekrule

import rule._
import java.time.LocalDate
import java.time.DayOfWeek

class CurrentDayOfWeekRule extends Rule{
  override def message(): String = "Your password must include the \u001B[4mcurrent day of the week\u001B[24m."
  override def check(password: String): Boolean = {
    val now = LocalDate.now()
    val currentDayOfWeek = now.getDayOfWeek.toString.toLowerCase
    password.toLowerCase.contains(currentDayOfWeek)
  }
}