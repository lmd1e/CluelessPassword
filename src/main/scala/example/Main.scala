package example

import scala.io.StdIn

import rule._
import minlengthrule._
import uppercaserule._
import timerule._
import lengthrule._
import leapyearrule._
import periodictablerule._
import romanrule._
import monthrule._
import lowercaserule._
import specialcharacterrule._
import sumequalrule._
import currentmonthrule._
import dayofweekrule._
import currentdayofweekrule._

val Rules = List[Rule](
  new MinLengthRule,
  new UppercaseRule,
  new LowercaseRule,
  new SpecialCharacterRule,
  new SumEqualRule,
  new MonthRule,
  new DayOfWeekRule,
  new RomanRule,
  new PeriodicTableRule,
  new LeapYearRule,
  new LengthRule,
  new CurrentMonthRule,
  new CurrentDayOfWeekRule,
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