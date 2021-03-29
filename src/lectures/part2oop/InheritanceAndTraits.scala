package lectures.part2oop

object InheritanceAndTraits extends App {

  // Single class inheritance
  sealed class Animal {

    val creatureType = "wild"

    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch


  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding

  /* class Dog(dogType: String) extends Animal {
    override val creatureType: String = dogType
    override def eat: Unit = println("crunch, crunch")
  } */

  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K10")
  unknownAnimal.eat

  // overRIDING != overLOADING

  // super

  // preventing overrides
  // 1 - use the `final` keyword on member
  // 2 - use the `final` keyword on entire class
  // 3 - seal the class = extended classes in THIS FILE, but prevent
  //                      extension in other files.
}
