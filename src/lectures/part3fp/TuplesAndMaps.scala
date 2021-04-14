package lectures.part3fp

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
}
