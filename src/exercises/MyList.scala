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


  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
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

  // hofs
  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start

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

  // hofs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    // TODO: Insert function is not Tail Recursion. Please, convert it.
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head)<= 0 ) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
     if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
     else Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /*
    [1, 2, 3].fold(0)(+)

    => [2, 3].fold(1)(+)
    => [3].fold(3)(+)
    => [].fold(6)(+)
    => 6

   */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start, h)
    t.fold(newStart)(operator) // we can do it in only one line: t.fold(operator(start, h))(operator)
  }
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(elem => elem * 2).toString)
  // shorter notation
  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)
  // shorter notation
  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  // shorter notation doesn't work for this lambda because we use element two times in the implementation.
  println(listOfIntegers.flatMap(element => Cons(element, Cons(element + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)


  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))

}