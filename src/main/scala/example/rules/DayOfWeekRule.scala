package dayofweekrule 

import rule._

class DayOfWeekRule  extends  Rule{
  val days = List("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
  override def message(): String = "Your password must include a \u001B[4mday of the week\u001B[24m."
  override def check(password: String): Boolean = days.exists(password.toLowerCase.contains)
}

