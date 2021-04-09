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

  /*
    Exercise:

    1. Generic trait MyPredicate[-T]: it will have a small method to test whether a value
      type T passes a condition. So, every subclass of my predicative team will actually
      have a different implementation of that little method which test whether the T passes
      that condition.

      // -T is contravariant
      Generic trait MyPredicate[-T] with a little method test(T) => Boolean

    2. Generic trait MyTransformer[-A, B]: it will have a small method to convert a value of
      type A into value of type B. Every subclass of MyTransformer will have a different
      implementation of that method.

      // -A is contravariant
      Generic trait MyTransformer[-A, B] with a method transform(A) => B

    3. Implement in MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B])

      For example, we have the following class:

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      So, if you've implemented my predicate in my transformer correctly then the map,
      filter and flatMap should work as follow:

      [1,2,3].map(n * 2) = [2,4,6]
      [1,2,3,4].filter(n % 2) ? [2,4]
      [1,2,3].flatMap(n => [n, n + 1]) => [1,2, 2,3, 3,4]

   */


}
