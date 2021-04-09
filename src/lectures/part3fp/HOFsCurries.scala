package lectures.part3fp

object HOFsCurries extends App {

  // How you read the following function
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // higher order functions (HOF)
  // map, flatMap, filter in MyList

  // we want to define a function that applies a function another function in
  // times over a given value, i.e.
  // function that applies a function n times over a value x
  //
  // nTimes(f, n, x)
  //
  // where:
  // f: the function that will be applied
  // n: number of applications of the function f
  // x: is the subject of the application of this function
  //
  // for example:
  //
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  //
  // In general:
  //
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n- 1, f(x))

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))


  //nTimesBetter(f,n) = x => f(f(f(...(x)))
  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x // identity function
    else (x: Int) => nTimesBetter(f, n -1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))


  // curried functions
  // val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y

  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyList
      - foreach method A => Unit
        [1, 2, 3].foreach(x => println(x))

      - sort function ( (A, A) => Int) => MyList
        [1,2,3].sort((x, y)=> y - x) => [3, 2, 1]

      - zipWith function (list, (A, A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6]), x * y) => [1 *4, 2*5, 3*6] = [4, 10, 18]

      - fold function: fold(start)(function) => a value
        [1, 2, 3].fold(0)(x + y) = 1 + 2 + 3 = 6

    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

    3. compose(f, g) => x => f(g(x))
        andThen(f,g) => x => g(f(x)) min 6.40

   */
    def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
      x => y => f(x, y)

    def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
      (x, y) => f(x)(y)


    /*
      Compose function for Int

      def compose(f: Int => Int, g: Int => Int): Int => Int =
        x => f(g(x))
    */

    /*
      Compose function in General
     */
    def compose[A, B, T](f: A => B, g: T => A): T => B =
      x => f(g(x))

    /*
      andThen function for Int

    def andThen(f: Int => Int, g: Int => Int): Int => Int =
      x => g(f(x))
    */

    /*
      andThen function in General
     */
    def andThen[A, B, C](f: A => B, g: B => C): A => C =
      x => g(f(x))

    // Testing
    def superAdder2: (Int => Int => Int) = toCurry(_ + _)
    def add4 = superAdder2(4)
    println(add4(17))

    val simpleAdder = fromCurry(superAdder)
    println(simpleAdder(4, 17))

    val add2 = (x: Int) => x + 2
    val times3 = (x: Int) => x * 3
    val composed = compose(add2, times3)
    val ordered = andThen(add2, times3)

    println(composed(4))
    println(ordered(4))



}
