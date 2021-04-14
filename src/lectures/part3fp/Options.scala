package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // Options were invented to deal with unsafe APIS.

  /**
   * UnsafeMethod supposed to return a String, but due to
   * some implementation it seems to return a `null`.
   *
   * So, say that someone else wrote this method and through one
   * of the code paths it returns `null`
   *
   * @return String
   */
  def unsafeMethod(): String = null

  // val result = Some(unsafeMethod()) // this is WRONG, because you can receive null and you get Some(null)
                                       // which basically breaks the whole point of options.

  val result = Option(unsafeMethod()) // Some or None
  println(result)

  /*
    The whole point of options is that we should never do no checks ourselves,
    the option type will do this for us.
   */

  // chained methods
  def backupMethod(): String = "A valid result"

  // this how you work with unsafe APIs
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIS, then make your methods return option of something
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ == 4))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehension


  val config: Map[String, String] = Map(
    /*
      this values were fetched from elsewhere (maybe a config file),
      but we don not have the certainty that the key host and the
      key port have values inside this map.
     */
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  /*
  Exercise.

    Try to establish a connection, if so - print the connect method.
 */

  // Solution 1
  val host = config.get("host")
  val port = config.get("port")

  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h,p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)

  // if (connectionStatus == null) println(None) else print(Some(connectionstatus.get))
  println(connectionStatus)

  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)


  // Solution 2:  chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // Solution 3: for comprehension
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)


}
