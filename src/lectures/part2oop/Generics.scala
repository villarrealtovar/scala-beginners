package lectures.part2oop

object Generics extends App {

  // Generic class
  // `A` is a type parameter
  class MyList[A] {
    // use the type A
  }

  // `Key`, `Value` is a type parameter
  class MyMap[Key, Value]

  // Also works for traits
  trait MyListTrait[A] {
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods

  object MyList {
    // method type parameterised with `A`
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]


  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Good question is: if Cat extends Animal, does a list of cat also extend the list of animal?
  // answers:
  // 1. yes, List[Cat] extends List[Animal] => it's called COVARIANCE
  // `+A` indicates this is a covariant list
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // now, can I actually add any animal to it?
  // animalList.add(new Dog) ??? Hard question


  // 2. No. ListCat and ListAnimal are two separated things = it's called INVARIANCE.
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] // <== Error

  // 3. Hell, no! CONTRAVARIANCE
  // `-A` indicates this is a contravariant list
  class Trainer[-A]
  val contravarianceTrainer: Trainer[Cat] = new Trainer[Animal]


  // bounded types
  // bounded types allow you to use your generic classes only for certain
  // types that are either a subclass of a different type or a superclass
  // o a different type.
  // upper bounded types
  class Cage[A <: Animal](animal: A) // class Cage only accepts type parameters which
                                    // are subtypes of Animals
  val cage = new Cage(new Dog)

  class Car
  val newCage = new Cage(new Car)


  // lower bounded types
  class CageLower[A >: Animal](animal: A) // class CageLower only accept something which
                                          // is a supertype of Animal

  // bounded types solve a variance problem which is very annoying when
  // when we want to write covariant collection

  class MyListCovariant[+A] {
    // def add(element: A): MyList[A] = ??? // <- ERROR => Covariant type A occurs in contravariant position in type A of value elemen
                                            // What that means? This is related to HARD QUESTION.
                                            // how to solve it?

    def add[B >: A](element: B): MyList[B] = ???

    /*
      A = Cat
      B = Dog which is in particular an Animal

      if I add an Animal to a list of cats, then that will turn into a list
      of animals because there are cats and dogs now.
     */

    // Answer to HARD QUESTION: we return a list of Animals


  }




}
