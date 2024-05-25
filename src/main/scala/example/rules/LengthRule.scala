package lengthrule

import rule._

class LengthRule extends  Rule{
  override def message(): String = "Your password must include a \u001B[4mits length\u001B[24m."
  override def check(password: String): Boolean = password.contains(password.length.toString)
}