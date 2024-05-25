package monthrule

import rule._

class MonthRule extends  Rule{
  val months = List("january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december")
  override def message(): String = "Your password must include a \u001B[4mmonth of the year\u001B[24m."
  override def check(password: String): Boolean = months.exists(password.toLowerCase.contains)
}

