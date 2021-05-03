package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // 1 - Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }


  // 2 - match anything
  // 2.1 wildcard

  val matchAnything = x match {
    case _ =>  // return whatever
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 -  tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // Pattern Matches can be NESTED!

  // 4 - case classes - this is called `constructor pattern`
  // Pattern Match can be nested with Case Classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,38)
  val standarListMatching = aStandardList match {
    case List(1, _, _, _) =>  // extractor <- advanced
    case List(1, _*) => // var args pattern = a list of arbitrary length <- advanced
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 38 => // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // name binding => use the name later (here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested pattern
  }

  // 8 - multi patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // compound pattern (or multi-pattern)
    case _ =>
  }

  // 9 - if guards
  val secondElement = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => //guard
  }

  /*
    Question.
   */

  val numbers = List(1,2,3)
  val numberMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numberMatch) // what would it print?
  // JVM trick question
}
