package lectures.part3fp

object AnonymousFunctions extends App {
  /* val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = x * 2
  }*/

  // anonymous function (LAMBDA)
  // val doubler: Int => Int = (x: Int) => x * 2
  // val doubler: Int => Int = x => x * 2
  val doubler = (x: Int) => x * 2

  // multiple params in a lambda
  // val adder: (Int, Int) => Int  = (a: Int, b: Int) => a + b
  val adder = (a: Int, b: Int) => a + b

  // no params
  // val justDoSomething: () => Int = () => 3
  val justDoSomething = () => 3

  // CAREFUL
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR Syntactic sugar
  // val niceIncrementer: Int => Int =  (x: Int) => x + 1
  val niceIncrementer: Int => Int =  _ + 1 // equivalent to x => x + 1


  // val niceAdder: (Int, Int) => Int = (a, b) => a + b
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent (a, b) => a + b

  /*
    Exercises

    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder (from previous video) as Anonymous Function
   */

    // Solution for 2.
    val superAdd = (x: Int) => (y: Int) => x + y

    println(superAdd(3)(4))

}
