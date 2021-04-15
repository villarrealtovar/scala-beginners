package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the AOI
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for exercise
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  /*
     Exercise:
     if your get the html from the connection, print it to the console
     i.e call renderHTML
    */

    // answer 1
    val possibleConnection = HttpService.getSafeConnection(host, port)
    val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
    possibleHTML.foreach(renderHTML)

    // answer 2
    HttpService.getSafeConnection(host, port)
      .flatMap(connection => connection.getSafe("/home"))
      .foreach(renderHTML)

    // answer 3
    for {
      connection <- HttpService.getSafeConnection(host, port)
      html <- connection.getSafe("/home")
    } renderHTML(html)

  /*
    In imperative language:

    try {
      connection = HttpService.getConnection(host, port)
      try {
        connection.get("/get")
        renderHTML(page)
      } catch (some other exception) {

      }
    } catch (exception) {

    }
   */

}
