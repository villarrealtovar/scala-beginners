package lectures.part1basics

object ValuesVariablesTypes extends App {

  // Val are Immutable
  // `Compiler` can infer types
  val x: Int = 42
  println(x)

  val aString: String = "Hello Andres"
  val anotherString = "Goodbye"

  val aBoolean: Boolean = true // or false
  val aChar: Char = 'a' // <- in single quotes
  val aInt: Int = x
  val aShort: Short = 4613 // <- Int short
  val aLong: Long = 56465435431387454L // <- please note the `L` at the end. Without `L`, Scala
                                        // will complain that integer number is out of range
                                        // for type Int

  val aDouble: Double = 3.14
  val aFloat: Float = 2.0f // See the `f` at the end.


  // Variables
  var aVariable: Int = 4
  aVariable = 5 // Variables are used for in functional programming as "side effects" which are
                // useful because they allow us to see what our programs are doing.

}
