package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString

    case classes are an ideal solution to avoid boilerplate code.

    Case classes are an exceptionally useful shorthand for defining
    a class and the companion object and a lot of sensible defaults in one go.

   */

  case class Person(name: String, age: Int)

  // 1. class parameters are promoted to fields
  val thiago = new Person("Thiago", 3)
  // println(thiago.name)

  // 2. sensible toString
  println(thiago.toString) // without case class `thiago.toString` prints `lectures.part2oop.CaseClasses$Person@2f7a2457`
  println(thiago) // println(instance) = println(instance.toString)  // syntactic sugar

  // 3. equals and hashCode implemented Out Of The Box
  val thiago2 = new Person("Thiago", 3)
  println(thiago == thiago2) // without case class, this prints `false` because thiago and thiago2 are two different
                            // instances of class person, but the equals method was not implemented.

  // 4. Case Classes have handy copy methods.
  val thiago3 = thiago.copy() //  `copy` creates a new instance of this case class. Also, the `copy` method
                              // receive named parameters

  val thiago4 = thiago.copy(age = 2)
  println(thiago4)

  // 5. Case Classes have companion objects
  val thePerson = Person // valid
  val caro = Person("Caro", 37) // `apply` method. So, the companion object's apply method does the same
                                // thing  as the constructor. So, in practice, we don't really use the keyword
                                // `new` when instantiating a case class we only use this form.

  // 6. Case Classes are serializable which makes cases classes especially useful when
  //    dealing with distributed systems: You can send instances of case classes through
  //    the network and in between JVM. Important in AKKA Framework.
  //    Akka deals with sending serializable messages through the network and our messages
  //    are in general, in practice, case classes.


  // 7. Case classes have extractor patterns = that means that case classes can be used in Pattern Matching

  // `case objects` have the same properties as `case classes` except they don't get companion
  // objects because they are their own companion objects.
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
