package specialcharacterrule

import rule._

class SpecialCharacterRule extends  Rule{
  val specialCharacters = "!@#$%^&*()-+=".toSet
  override def message(): String = "Your password must include a \u001B[4mspecial character\u001B[24m."
  override def check(password: String): Boolean = password.exists(specialCharacters.contains)
}