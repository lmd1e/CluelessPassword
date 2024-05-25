package minlengthrule

import scala.util.Random
import rule._

class MinLengthRule extends  Rule{
  val length = Random.between(4, 11)
  override def message(): String = s"Your password must be at least \u001B[4m${length} characters\u001B[24m."
  override def check(password: String): Boolean = password.length >= length
}