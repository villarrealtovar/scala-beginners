package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangoutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favoriteMovie)

    // important: after unary_! leaves an space for the :. Otherwise, this throws error
    def unary_! : String = s"$name what the heck!!!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive:Boolean = true

    def learns(thing: String): Unit = println(s"$name is learning $thing")
    def learnsScala(): Unit = this learns "Scala"

    // parenthesis are important
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation or operator notation. These are example of syntactic sugar

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")

  println(mary hangoutWith tom)
  println(mary.+(tom))
  println(mary + tom)

  // all operators ARE METHODS
  println(1 + 2)
  println(1.+(2))

  // Akka actors has operators as `!` or `?`


  // prefix notation (another form of syntactic sugar)
  // all is about unary operators
  val x = -1 // here the - is a unary operator

  // unary operators are also methods
  val y = 1.unary_-  // is equivalent to -1

  // unary_ prefix operator works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notations: only available for methods without parameters<
  println(mary.isAlive)
  println(mary isAlive)

  // special method: apply
  println(mary.apply())
  println(mary()) //equivalent

  /*
    Exercises

    1. Overload the + operator
      For example:
      mary + "the rockstar" => returns a new person "Mary (the rockstar)"

    2. Add an age (with default 0) to the Person class
       Add a unary + operator => returns a new person with the age + 1
       For example:
       +mary => mary with the age incremented

    3. Add a "learns" method in the Person class. This receives a string parameter
    and returns a sentence which says => Mary learns ${the string parameter}

    Then add a "learnsScala" method which doesn't receive any parameter and
    calls the "learns" method with "Scala". Use it in postfix notation

    4. Overload the apply method to receive a number and returns a String
      For example:
      mary.apply(2) => "Mary watches {favoriteMovie} 2 times"
   */
  println(mary + "the rockstar") //prints the new Person reference
  println((mary + "the rockstar")()) // apply method on new Person object
  println((mary + "the rockstar").apply())

  println((+mary).age)

  mary learnsScala

  println(mary(10))

}
