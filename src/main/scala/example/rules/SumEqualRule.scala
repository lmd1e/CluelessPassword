package sumequalrule

import scala.util.Random
import rule._

class SumEqualRule extends  Rule{
  val sum = Random.between(15, 45)
  override def message(): String = s"The digits in your password \u001B[4mmust add up to ${sum}\u001B[24m."
  override def check(password: String): Boolean = password.filter(_.isDigit).map(_.asDigit).sum == sum
}