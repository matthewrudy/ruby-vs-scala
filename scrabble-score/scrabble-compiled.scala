object ScrabbleCompiled {
  
  def main(args:Array[String]) {

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
  }
}
