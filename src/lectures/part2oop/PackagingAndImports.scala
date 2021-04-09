package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // 1. package member are accessible by their simple name
  val writer = new Writer("Andres", "Scala rocks!", 2021)

  // 2. import the package
  val princess = new Princess
  // val princess = new playground.Cinderella // Fully Qualified Class Name


  // 3. packages are in hierarchy
  // matching folder structure

  // 4. package object
  sayHello
  println(SPEED_OF_LIGTH)

  // 5. imports
  val prince = new PrinceCharming

  // 6. alias
  val date = new Date
  // val sqlDate = new SqlDate(2021, 3, 2) // Constructor deprecated, but it's just for sake of example

  // 7. default imports
  // java.lang - String, Object, Exception, ...
  // scala - Int, Nothing, Function, ...
  // scala.Predef - println, ???, ...
}
