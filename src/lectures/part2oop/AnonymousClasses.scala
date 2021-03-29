package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahahahaha")
  }

  println(funnyAnimal.getClass)

  /*
      The anonymous class is equivalent with (behind scenes):

      class AnonymousClasses$$anon$1 {
        override def eat: Unit = println("hahahahahahaha")
      }

      val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service")
  }



}
