package currentmonthrule

import rule._
import java.time.LocalDate
import java.time.Month

class CurrentMonthRule extends Rule{
  override def message(): String = "Your password must include the \u001B[4mcurrent month\u001B[24m."
  override def check(password: String): Boolean = {
    val now = LocalDate.now()
    val currentMonth = now.getMonth.toString.toLowerCase
    password.toLowerCase.contains(currentMonth)
  }
}