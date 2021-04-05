package lectures.part2oop

import jdk.nashorn.internal.ir.RuntimeNode

object Exceptions extends App {

  val x: String = null
  // println(x.length) // this will crash with NullPointException

  // 1. throwing exceptions
  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extends the Throwable class
  // Exception and Error are the major Throwable subtypes
  //
  // Exceptions denote something that wrong with the program e.g. NullPointerException
  // Errors are something that happened wrong with the system. e.g. stack overflow error.

  // 2. how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 38

  val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT

    // `finally` is optional
    // `finally` doesn't influence the return type of this expression
    // use `finally` ONLY for side effects.
    println("finally")
  }

  println(potentialFail)


  // 3. how to define your own Exceptions
  class MyException extends Exception
  val exception = new MyException
  // throw exception

  /*
    Exercises.

    1. Crash your program with an OutOfMemoryError
    2. Crash with StackOverflowError
    3. Custom PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throw
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) exceed Int.MIN_VALUE
          - MatchCalculationException for division by 0
   */

  // Solution for 1.
  // val array = Array.ofDim(Int.MaxValue) // <= This is called OOM

  // Solution for 2.
  def infinite: Int = 1 + infinite
  // val noLimit = infinite


  // Solution for 3.
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if  (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else  result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  // println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))



}
