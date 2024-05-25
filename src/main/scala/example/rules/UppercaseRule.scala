package uppercaserule

import rule._

class UppercaseRule extends  Rule{
  override def message(): String = "Your password must include an \u001B[4muppercase letter\u001B[24m."
  override def check(password: String): Boolean = password.exists(_.isUpper)
}