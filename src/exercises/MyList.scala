package exercises

abstract class MyList[+A] {

  /*
    API

    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString = a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // Higher-Order Functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  /**
   * Concatenation function
   */
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object  Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

/**
 *
 * @param h - head
 * @param t - tail
 */
case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
    Example:

    [1,2,3].filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = elem % 2 == 0
    }) // => sugar syntax would be [1,2,3].filter(n % 2 == 0)

    => [2, 3].filter(n % 2 == 0)
    => new Cons(2, [3].filter(n % 2 == 0))
    => new Cons(2, Empty.filter( n % 2 == 0))
    => new Cons(2, Empty))

   */
  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    Example:

    [1,2,3].map(new MyTransformer[Int, Int] {
      override def transform(element: Int): Int = element * 2
    }) // => sugar syntax would be [1,2,3].map(n * 2)

      = new Cons(2, [2,3].map(n*2))
      = new Cons(2, new Cons(4, [3].map(n*2))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))

   */
  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
    Example:

    [1, 2] ++ [3, 4, 5]

    => new Cons(1, [2] ++ [3,4,5])
    => new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
    => ...
    => new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))

   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  /*
    Example:

    [1,2].flatMap(n => [n, n + 1])

    => [1,2] ++ [2].flatMap(n => [n, n + 1])
    => [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1])
    => [1,2] ++ [2,3] ++ Empty
    => [1,2,2,3]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = Cons(element, Cons(element + 1, Empty))
  }).toString)

  print(cloneListOfIntegers == listOfIntegers)

}