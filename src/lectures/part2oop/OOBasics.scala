package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Thiago", 3)
  println(person.x)
  person.greet("Caro")
  person.greet()
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


