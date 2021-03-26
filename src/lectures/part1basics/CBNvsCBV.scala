package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value " + x)
    println("by value " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name " + x)
    println("by name " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  // --------------------------------------------

  def infinite() : Int =  1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) // <- here the program crashes because stack overflow (infinite)
  printFirst(34, infinite())  // <- program survives because `infinite` function is not evaluated inside
                              // of the printFirst function body, therefore never is used.



}
