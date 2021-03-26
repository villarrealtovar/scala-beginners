package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // `1 + 2` is called an Expression. Expressions are
                // evaluated to have value and they have a type.
  println(x)

  println(2 + 3 * 4) // Math Expressions are:
                    // commons: + - * /
                    // bitwise operators: & (bitwise and), | (bitwise or),
                    //                    ^ (bitwise exclusive or), << (bitwise left shift)
                    //                    >> (bitwise right shift)
                    //                    >>> (right shift with zero extension) -- Only in Scala

  println(1 == x) // `1 == x` is an expression and evaluates to Boolean. In this case the value is `false`
                  // ==   !=   >   >=  <   <=

  println(!(1 == x)) // logical negation is unary operator
                    // !    &&    ||

  var aVariable = 2
  aVariable += 3 // also works with  -=  *=  /=   ... only works on `vars` (side effects)
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)
  //
  // Instruction: it's something that you tell to the computer to do. For example,
  //              print to the console, send this to the server, etc.
  //
  // Expression: it's something that have value and or a type.
  //
  // Instruction means DO something versus Expression means GIVE me the value of something.

  // `if` expression
  val aCondition = true
  val aConditionValue = if (aCondition) 7 else 3 // `if` returns a value. => that is why is called "if expression"
  println(aConditionValue)
  println(if (aCondition) 9 else 3)
  println(1 + 3)

  // loops
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE `WHILE` AGAIN: This is because while and looping in general
  // is very specific to Imperative Programming

  // EVERYTHING in Scala is an Expression. Only definitions like the definition
  // of a `val` or a `class` or `package` are not expressions, but everything else is.

  val aWeirdValue = (aVariable = 3) // `aWeirdValue` is the type `Unit`. `Unit` is a very
                                    // special type in Scala which is void in other languages.
                                    //
                                    // Unit === void
                                    //
                                    // side effects in Scala (for example aVariable = 3) return Unit

  println(aWeirdValue) //  () is the only value that `Unit` type can hold

  // Examples of side effects: println(), while, reassigning variables.
  // side effect are like instructions, but in Scala they are still expression returning Unit.

  // Code blocks are also expression and the value is given by the last expression inside of the
  // code block.
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }


}
