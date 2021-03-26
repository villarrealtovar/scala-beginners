package lectures.part1basics

object DefaultArgs extends App {

  // tr means tail recursive
  def trFactorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFactorial(n-1, n * acc)

  val fact10 = trFactorial(10)
  println(fact10)


  def savePicture(format:String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  savePicture(width = 800)

  /*
    1. pass in every leading argument
    2. name the argument
   */

  savePicture(height = 600, width = 800, format = "bmp")

}
