package lectures.part3fp

object WhatsAFunction extends App {
  // the whole purpose of the functional programming section
  // is to use and work with functions as first class elements.

  // DREAM: use functions as first class elements
  // Problem: we come from OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // doubler acts as a function

  // function types = Function[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS OR INSTANCES OF CLASSES DERIVING
  // FROM Function1, Function2, ... Function22


  /*
    Exercises

    1. a function which takes 2 string and concatenates them
    2. transform the MyPredicate and MyTransformer into function types (min 12.00)
    3. define a function which takes an int and returns another function
       which takes an int and return an int
       - what's the type of this function
       - how to do it
   */

  // Solution for 1.
  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  println(concatenator("Hello ", "Scala"))

  // Solution for 3.

  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried  functions => have the property that they can be called with
                            // multiple parameter just by their mere definition. So, a curry function
                            // actually receives some kind of parameter and returns another function
                            // which receives parameters

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
