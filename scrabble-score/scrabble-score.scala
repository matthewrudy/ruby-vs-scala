object Dictionary {
  
  val WORDS = io.Source.fromFile("dict/3letters").getLines.toSet ++
              io.Source.fromFile("dict/4letters").getLines.toSet
  
  def isWord(word:String) = WORDS contains word
  
  /*scrabble scores*/
  val LETTER_SCORES = Map(
    "A" -> 1,
    "B" -> 3,
    "C" -> 3,
    "D" -> 2,
    "E" -> 1,
    "F" -> 4,
    "G" -> 2,
    "H" -> 4,
    "I" -> 1,
    "J" -> 8,
    "K" -> 5,
    "L" -> 1,
    "M" -> 3,
    "N" -> 1,
    "O" -> 1,
    "P" -> 3,
    "Q" -> 10,
    "R" -> 1,
    "S" -> 1,
    "T" -> 1,
    "U" -> 1,
    "V" -> 4,
    "W" -> 4,
    "X" -> 8,
    "Y" -> 4,
    "Z" -> 10
  )
  
  def letterScore(character:Char) = LETTER_SCORES(character.toString)
  
  def scrabbleScore(word:String) = {
    word.foldLeft(0) {
      (sum, letter) => sum + letterScore(letter)
    }
  }

}

val repeats = args(0).toInt
val verbose = (args.size > 1)

println(repeats+" repeats")

var wordsCalculated = 0

for(i <- 1 to repeats) {
  Dictionary.WORDS.foreach { word =>
    Dictionary.isWord(word)
    Dictionary.scrabbleScore(word)
    
    wordsCalculated = wordsCalculated + 1
  }
  
  if(verbose) {
    println("repeat #"+i)
  }
}

println(wordsCalculated+" words calculated")