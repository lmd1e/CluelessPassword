package example

import scala.io.StdIn
import scala.util.Random
import java.time.LocalTime
import scala.util.control.Breaks._


abstract class Rule{
  def message(): String
  def check(password: String): Boolean 
}

class MinLengthRule extends  Rule{
  val length = Random.between(4, 11)
  override def message(): String = s"Your password must be at list ${length} characters"
  override def check(password: String): Boolean = password.length >= length
}

class UppercaseRule extends  Rule{
  override def message(): String = "Your password must include an uppercase letter"
  override def check(password: String): Boolean = password.exists(_.isUpper)
}

class SpecialCharacterRule extends  Rule{
  val specialCharacters = "!@#$%^&*()-+=".toSet
  override def message(): String = "Your password must include a special character"
  override def check(password: String): Boolean = password.exists(specialCharacters.contains)
}

class SumEqualRule extends  Rule{
  val sum = Random.between(15, 45)
  override def message(): String = s"The digits in your password must add up to ${sum}"
  override def check(password: String): Boolean = password.filter(_.isDigit).map(_.asDigit).sum == sum
}

class MonthRule extends  Rule{
  val months = List("january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december")
  override def message(): String = "Your password must include a month of the year"
  override def check(password: String): Boolean = months.exists(password.toLowerCase.contains)
}

class RomanRule extends  Rule{
  val RomanCharacters = "IVXLCDM".toSet
  override def message(): String = "Your password must include a roman numeral"
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
  override def message(): String = "Your password must include a two letter symbol from the periodic table"
  override def check(password: String): Boolean = periodicTable.exists(password.contains)
}

class LeapYearRule extends  Rule{
  override def message(): String = "Your password must include a leap year."
  override def check(password: String): Boolean = (password.split("\\D+").filter(_.nonEmpty)).exists (chunk => chunk.toInt % 4 == 0)
}

class LengthRule extends  Rule{
  override def message(): String = "Your password must include a its length."
  override def check(password: String): Boolean = password.contains(password.length.toString)
}


class TimeRule extends Rule{
  val currentTime = LocalTime.now()
  val time = currentTime.getHour * 100 + currentTime.getMinute
  override def message(): String = "Your password must include the current time {HHMM}"
  override def check(password: String): Boolean = password.contains(time.toString)
}

val Rules = List[Rule](
    new MinLengthRule,
    new UppercaseRule,
    new SpecialCharacterRule,
    new SumEqualRule,
    new MonthRule,
    new RomanRule,
    new PeriodicTableRule,
    new LeapYearRule,
    new LengthRule,
    new TimeRule
  )

@main def main(args: String*): Unit = {
  println(s"Enter password ${args.mkString}")
  val input = StdIn.readLine()
  var GameRules = List.empty[Rule]

  for (i <- 0 until Rules.length){
    val rule = Rules.apply(i)
    GameRules = GameRules.appended(rule)
    println(rule.message())

    var quit = false
    while (!quit){
      val input = StdIn.readLine()
      quit = (GameRules.forall( gameRule => gameRule.check(input) ))
    }
  }
}