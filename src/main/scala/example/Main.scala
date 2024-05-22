package example

import scala.io.StdIn
import scala.util.Random
import java.time.LocalTime

abstract class Rule{
  def message(): String
  def check(password: String): Boolean 
}

class MinLengthRule extends  Rule{
  val length = Random.between(4, 11)
  override def message(): String = s"Your password must be at least \u001B[4m${length} characters\u001B[24m."
  override def check(password: String): Boolean = password.length >= length
}

class UppercaseRule extends  Rule{
  override def message(): String = "Your password must include an \u001B[4muppercase letter\u001B[24m."
  override def check(password: String): Boolean = password.exists(_.isUpper)
}

class LowercaseRule extends  Rule{
  override def message(): String = "Your password must include an \u001B[4mlowercase letter\u001B[24m."
  override def check(password: String): Boolean = password.exists(_.isLower)
}

class SpecialCharacterRule extends  Rule{
  val specialCharacters = "!@#$%^&*()-+=".toSet
  override def message(): String = "Your password must include a \u001B[4mspecial character\u001B[24m."
  override def check(password: String): Boolean = password.exists(specialCharacters.contains)
}

class SumEqualRule extends  Rule{
  val sum = Random.between(15, 45)
  override def message(): String = s"The digits in your password \u001B[4mmust add up to ${sum}\u001B[24m."
  override def check(password: String): Boolean = password.filter(_.isDigit).map(_.asDigit).sum == sum
}

class MonthRule extends  Rule{
  val months = List("january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december")
  override def message(): String = "Your password must include a \u001B[4mmonth of the year\u001B[24m."
  override def check(password: String): Boolean = months.exists(password.toLowerCase.contains)
}

class RomanRule extends  Rule{
  val RomanCharacters = "IVXLCDM".toSet
  override def message(): String = "Your password must include a \u001B[4mroman numeral\u001B[24m."
  override def check(password: String): Boolean = password.exists(RomanCharacters.contains)
}

class PeriodicTableRule extends  Rule{
  val periodicTable = Set("He", "Li", "Be","Ne", "Na", "Mg",
  "Al", "Si", "Cl", "Ar", "Ca", "Sc", "Ti", "Cr",
  "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr",
  "Rb", "Sr", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd",
  "In", "Sn", "Sb", "Te", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
  "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf",
  "Ta", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po",
  "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "Np", "Pu", "Am", "Cm",
  "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr")
  override def message(): String = "Your password must include a two letter \u001B[4msymbol from the periodic table\u001B[24m."
  override def check(password: String): Boolean = periodicTable.exists(password.contains)
}

class LeapYearRule extends  Rule{
  override def message(): String = "Your password must include a \u001B[4mleap year\u001B[24m."
  override def check(password: String): Boolean = (password.split("\\D+").filter(_.nonEmpty)).exists (chunk => chunk.toInt % 4 == 0)
}

class LengthRule extends  Rule{
  override def message(): String = "Your password must include a \u001B[4mits length\u001B[24m."
  override def check(password: String): Boolean = password.contains(password.length.toString)
}


class TimeRule extends Rule{
  override def message(): String = "Your password must include the \u001B[4mcurrent time {HHMM}\u001B[24m."
  override def check(password: String): Boolean = {
    val currentTime = LocalTime.now()
    val time = currentTime.getHour * 100 + currentTime.getMinute
    password.contains(time.toString)
  }
}

val Rules = List[Rule](
    new MinLengthRule,
    new UppercaseRule,
    new LowercaseRule,
    new SpecialCharacterRule,
    new SumEqualRule,
    new MonthRule,
    new RomanRule,
    new PeriodicTableRule,
    new LeapYearRule,
    new LengthRule,
    new TimeRule
  )

def inputFunc(): String = {
  print("\u001B[34m > ")
  val input = StdIn.readLine()
  print("\u001B[0m")
  input
}

@main def main(args: String*): Unit = {
  println(s"\u001B[33mEnter password ${args.mkString}\u001B[0m")

  val input = inputFunc()

  var GameRules = List.empty[Rule]

  for (i <- 0 until Rules.length){
    val rule = Rules.apply(i)
    GameRules = GameRules.appended(rule)
    println(s"\u001B[33m${rule.message()}\u001B[0m")

    var quit = false
    while (!quit){
      val input = inputFunc()
      quit = (GameRules.forall( gameRule => gameRule.check(input) ))
      if (!quit){
        println("\u001B[3m\u001B[31mPassword incorrect:\u001B[0m")
        val rulesErrors = GameRules.map(errorRule => (errorRule.message(), errorRule.check(input)))
        rulesErrors.zipWithIndex.foreach { case ((message, result), index) =>
          val color = if (result) "\u001B[32m" else "\u001B[31m" 
            println(s"$color ${index + 1} Rule: $message \u001B[0m")
        }
      }
    }
    println("\u001B[3m\u001B[32mPassword correct\u001B[0m")
  }
  println(s"\u001B[31m${skull}\u001B[0m")
}






























































var skull ="""
  ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠉⠁⠄⠄⠄⠄⠈⠉⠉⠛⠿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⠻⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸⣿⣿⣿⣿
  ⣿⣿⣿⡇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣿⣿⣿⣿
  ⣿⣿⣿⡇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣿⣿⣿⣿
  ⣿⣿⣿⣇⠄⠄⠄⢀⣀⣀⣀⠄⠄⠄⠄⠄⢀⣀⣀⣀⡀⠄⠄⢠⣿⣿⣿⣿
  ⣿⣿⣿⣿⡄⠄⣼⣿⣿⣿⣿⣷⠄⠄⠄⢀⣿⣿⣿⣿⣿⠄⠄⣼⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⠄⠹⣿⣿⣿⣿⠏⣰⣿⣷⡀⢿⣿⣿⣿⡿⠄⢸⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⠄⠄⠉⠛⠛⠁⢠⣿⣿⣿⣷⠄⠙⠛⠋⠄⠄⢸⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣄⣀⡀⠄⠄⠄⠈⠛⠋⠙⠋⠄⠄⠄⠄⣀⣀⣸⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⣿⣷⣀⡄⠄⢀⡀⣀⠄⠄⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣧⣿⣿⣟⣿⢸⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⣿⡇⠸⡏⠿⢿⡿⣿⠛⠏⢿⠁⣿⣿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠄⠈⠁⠄⠄⠄⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿
  ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿"""