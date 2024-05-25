package leapyearrule

import rule._

class LeapYearRule extends  Rule{
  override def message(): String = "Your password must include a \u001B[4mleap year\u001B[24m."
  override def check(password: String): Boolean = (password.split("\\D+").filter(_.nonEmpty)).exists (chunk => chunk.toInt % 4 == 0)
}