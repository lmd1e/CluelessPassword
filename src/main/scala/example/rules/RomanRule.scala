package romanrule

import rule._

class RomanRule extends  Rule{
  val RomanCharacters = "IVXLCDM".toSet
  override def message(): String = "Your password must include a \u001B[4mroman numeral\u001B[24m."
  override def check(password: String): Boolean = password.exists(RomanCharacters.contains)
}