package lectures.part3fp

import scala.util.Random


object Sequences extends App {
  /*
    Seq

    A (very) general interface for data structures that:
      - have a well defined order
      - can be indexed

    Supports various operations:
      - apply, iterator, length, reverse for indexing and iterating
      - concatenation, appending, prepending
      - a lot of others: grouping, sorting, zipping, searching, slicing
   */
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,7, 6))
  println(aSequence.sorted)

  // Ranges
  // using `to` includes last number (in this case, 10)
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  // using `until` doesn't include last number (in this case, 10)
  val aRange2: Seq[Int] = 1 until 10
  aRange2.foreach(println)


  (1 to 10).foreach(x => println(s"Hello $x"))

  /*
    List

    A LinearSeq immutable linked list
      - head, tail, isEmpty methods are fast: O(1)
      - most operations are O(n): length, reverse

    Sealed -has two subtypes:
      - object Nil (empty)
      - class ::
   */
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 80
  println(prepended)

  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-|-"))

  /*
    Array

    The equivalent of simple Java arrays
      - can be manually constructed with predefined lengths
      - can be mutated (updated in place)
      - are interoperable with Java's T[] arrays
      - indexing is fast
   */
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // connection between arrays and sequences
  var numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  /*
    Vectors

    The default implementation for immutable sequences
      - effectively constant indexed read and write: O(log32(n))
      - fast element addition: append/prepend
      - implemented as fixed-branched trie (branch factor 32)
      - good performance for large sizes
   */
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // test vectors vs list

  val maxRuns = 1000
  val maxCapactity = 1000000
  /**
   * Getting the average right time for a collection
   */
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapactity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to  maxCapactity).toList
  val numbersVector = (1 to maxCapactity).toVector

  // advantage: keeps reference to tail
  // disavantage: updating an element in the middle takes long
  println(getWriteTime(numbersList))

  // advantage: depth of the tree is small
  // disavantage: needs to replace an entire 32-element chunck
  println(getWriteTime(numbersVector))

}
