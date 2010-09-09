import scala.actors._
import scala.actors.Actor._

object ScrabbleCompiledNActors {
  
  def main(args:Array[String]) {

    val repeats = args(0).toInt
    val N = args(1).toInt
    val verbose = (args.size > 2)
    
    println(repeats+" repeats")
    
    var wordsCalculated = 0
    val caller = self
    
    for(actorI <- 0 to N - 1) {
  
      actor {
      
        var actorsCount = 0
    
        for(i <- 1 to repeats if (i % N == actorI)) {
        
          Dictionary.WORDS.foreach { word =>
            Dictionary.isWord(word)
            Dictionary.scrabbleScore(word)
          
            actorsCount = actorsCount + 1
          }
        
          if(verbose) {
            println("repeat #"+i)
          }
        
        }
        
        caller ! N
      }
      
    }

    for(i <- 1 to N) {
      receive {
        case count:Int => wordsCalculated = wordsCalculated + count
      }
    }
    
    println(wordsCalculated+" words calculated")
  }
}
