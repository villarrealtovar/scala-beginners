package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangoutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    // important: after unary_! leaves an space for the :. Otherwise, this throws error
    def unary_! : String = s"$name what the heck!!!"
    def isAlive:Boolean = true

    // parenthesis are important
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
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
}
