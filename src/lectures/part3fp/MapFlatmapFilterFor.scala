package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3) // Standard List Implementation
  println(list)

  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /*
    Exercise:

    Print all combinations between two list
   */
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  // List ("a1", "a2", ... "d4")

  // "iterating"
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  val combinations2 = numbers.flatMap(n=> chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations2)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield ("" + c + n + "-" + color)
  println(forCombinations)

  val forCombinationsWithGuards = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield ("" + c + n + "-" + color)
  println(forCombinationsWithGuards)

  // with side effect
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    Exercises

    1. MyList supports for comprehensions?
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]

    2. A small collections of at most ONE element - Maybe[+T]
       Maybe[+T] can contains either Empty collection or a collection with only ONE
       element of type T
       implement:
       - map, flatMap, filter
   */

    // Maybe is ofter used in Functional Programming to denote optional values



}
