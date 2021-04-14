package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  /*
    Tuples = finite ordered "lists"
   */
  val aTuple = (2, "hello Scala") // type Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // prints 2

  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello Scala", 2)

  /*
    Maps - associates keys with values
      - keys -> values
   */
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(("Thiago", 555), ("Caro", 789))
  val phonebook2 = Map("Thiago" -> 555, "Caro" -> 789) // a -> b is sugar for (a, b)

  println(phonebook)
  println(phonebook2)

  // map operations
  println(phonebook.contains("Caro"))
  println(phonebook("Thiago"))
  // println(phonebook("Andres")) // <- throws NoSuchElementException because `Andres` key doesn't exist

  val phonebook3 = Map("Thiago" -> 555, "Caro" -> 789).withDefaultValue(-1)
  println(phonebook3("Andres"))

  // add a pairing
  val newPairing = "Lucia" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("T")))

  // mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Thiago", 555)).toMap)

  val names = List("Jose", "Andres", "Monica", "Lucia", "Lina")
  println(names.groupBy(name => name.charAt(0)))

  /*
    Exercises:

    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
    2. Overly simplified social network based on maps
        Person = String
          - add Person to the network
          - remove
          - friend (mutual)
          - unfriend

          - number of friends of a person
          - person with most friends
          - how many people have NO friends
          -if there is a social connection between two people (direct or not)

   */

  // Answer 1.
  val phonebook4 = Map(("jim", 555), ("JIM", 900))
  println(phonebook4) // it's not a problem to add to the map
  println(phonebook4.map(pair => pair._1.toLowerCase -> pair._2)) // careful, with mapping keys. Here, overlaps


  // Answer 2.
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAccum: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAccum
      else removeAux(friends.tail, unfriend(networkAccum, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)

  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))


  // small network Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.filterKeys(k => network(k).isEmpty).size  // alternatives:
                                                      // network.filterKeys(pair => pair._2.isEmpty).size
                                                      // network.count(k => network(k).isEmpty)
                                                      // network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}

