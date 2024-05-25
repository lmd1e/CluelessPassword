package lowercaserule

import rule._

class LowercaseRule extends  Rule{
  override def message(): String = "Your password must include an \u001B[4mlowercase letter\u001B[24m."
  override def check(password: String): Boolean = password.exists(_.isLower)
}