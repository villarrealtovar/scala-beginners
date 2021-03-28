package lectures.part2oop

object Objects extends App{

  // class level functionality: Functionality that doesn't
  // depend on an instance of a class: universal constants
  // or universal functionality that we should be able to
  // access without relying on instance of some class.

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static word doesn't exist in Scala")
  // SCALA actually has something better and it's called OBJECTS

  // this is the way that Scala defines "Class-Level Functionality"
  // Scala's objects don't receive parameters
  object Person { // type + its only instance
    // "static"/"class"-level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // In practice, we have factory methods inside objects
    def apply(mother: Person, father: Person): Person =
      new Person("Thiago")

  }

  class Person(val name: String) {
    // instance-level functionality

  }

  // COMPANIONS: Person object and  Person Class are companion because they
  // are in the same scope and have the same name.

  println(Person.N_EYES)
  println(Person.canFly)

  // we use objects as `singleton` instances
  // Scala Objects = SINGLETON INSTANCE

  val mary = Person
  val john = Person
  println(mary == john) // true

  val caro = new Person("Caro")
  val andres = new Person("Andres")
  println(caro == andres) // false

  val thiago = Person(caro, andres)

  // Scala applications = Scala object with a particular method
  // called `main`
  // def main(args: Array[String]): Unit
}
