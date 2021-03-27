package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Thiago", 3)
  println(person.x)
  person.greet("Caro")
  person.greet()

  // testing exercises
  val author = new Writer("Charles", "Dickens", 1812)
  val impostor = new Writer("Charles", "Dickens", 1812) // same values than `author` class, but..

  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAge)
  println(novel.isWritten(author))
  println(novel.isWritten(impostor)) // it's an impostor

  // Counter
  val counter = new Counter()
  counter.inc.print
  counter.inc.inc.inc.inc.print

  counter.inc(10).print

}

// constructor
class Person(name: String, val age: Int)  {
  // body
  val x = 2
  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors or overloading constructors
  def this(name: String) = this(name, 0)
  def this() = this("Andres")

}
// class parameters are NOT FIELDS


/*
  Exercises:

  1. Write Novel and Writer classes

  Writer: first name, sur name, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
*/
class Writer(firstName: String, surName: String, val year: Int) {
  def fullName(): String = s"$firstName $surName"
}


class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.year
  def isWritten(author: Writer) = author == this.author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}


/*
  2. Counter class
    - receives an int value
    - method current count
    - method to increment/decrement  => new Counter
    - overload inc/decr to receive an amount
 */

class Counter(val count: Int = 0) {
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  }
  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)


  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print: Unit = println(s"current count $count")
}


