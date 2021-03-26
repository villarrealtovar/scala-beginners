package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " I first need factorial of " + (n -1) )
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }

//  println(factorial(5000)) //<- crash with big number: stack over flow memory


  def anotherFactorial(n: Int): BigInt = {

    @tailrec // indicates to compiler that this function supposes to be tail recursive
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x-1, x * accumulator) // TAIL RECURSION = user recursive call as the LAST expression

    factorialHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper (10, 1)
    = factHelper (9, 10 * 1)
    = factHelper (8, 9 * 10 *1)
    = factHelper (7, 8 * 9 * 10 * 1)
    = ....
    = factHelper (2, 3 * 4 * ... * 10 * 1)
    = factHelper (1, 2 * 3 * 4 * ... * 10 * 1)
    = 2 * 3 * 4 * ... * 10 * 1
   */

  // println(anotherFactorial(5000))

  // When you need LOOPS, USE TAIL RECURSION

  /*
    Exercises

    1. Concatenate a string n times
    2. IsPrimer function tail recursive
    3. Fibonacci function tail recursive
   */

  // when you need loops, use Recursion
  @tailrec
  def concatenateTailRecursion(aString: String, n: Int, accum: String): String = {
    if (n <= 0) accum
    else concatenateTailRecursion(aString , n - 1, aString + accum)
  }
  println(concatenateTailRecursion("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRecursion(limit: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if(limit <= 1) true
      else isPrimeTailRecursion(limit -1, n % limit != 0 && isStillPrime)
    }
    isPrimeTailRecursion(n / 2, true)
  }

  println(isPrime(2003)) // true
  println(isPrime(629)) // false

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRecursion(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fibonacciTailRecursion(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibonacciTailRecursion(2, 1, 1)
  }

  println(fibonacci(8)) // 21

}
