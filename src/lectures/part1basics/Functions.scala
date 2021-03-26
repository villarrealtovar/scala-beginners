package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction)

  // when you need loops, use Recursion
  def aRepeatFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatFunction(aString, n - 1)
  }

  println(aRepeatFunction("hello", 3))


  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }


  /*
    Exercises

    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
    2. Factorial function  1 * 2 * 3 * .. * n
    3. A Fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n - 1) + f(n - 2)
    4. Test is a number is prime
   */

  def greeting(name: String, age: Int): Unit = println(s"Hi, my name is $name and I am $age years old")
  greeting("Carito", 25)



  def factorial(n: Int): Int = {
    if (n <= 0)  1
    else n * factorial(n - 1)
  }

  println(factorial(5))



  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(8))



  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(limit: Int): Boolean = {
      if (limit <= 1) true
      else n % limit != 0 && isPrimeUntil(limit-1)
    }

    isPrimeUntil(n / 2)

  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}


