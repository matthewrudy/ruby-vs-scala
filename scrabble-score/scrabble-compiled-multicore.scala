import scala.actors._
import scala.actors.Actor._

object ScrabbleCompiledMulticore {
  
  def main(args:Array[String]) {

    val repeats = args(0).toInt
    val verbose = (args.size > 1)
    
    println(repeats+" repeats")
    
    var wordsCalculated = 0
    val caller = self
    
    for(i <- 1 to repeats) {
    
      actor {
      
        var actorsCount = 0
        
        Dictionary.WORDS.foreach { word =>
          Dictionary.isWord(word)
          Dictionary.scrabbleScore(word)
          
          actorsCount = actorsCount + 1
        }
        
        caller ! actorsCount
        
        if(verbose) {
          println("repeat #"+i)
        }
      }
      
    }

    for(i <- 1 to repeats) {
      receive {
        case count:Int => wordsCalculated = wordsCalculated + count
      }
    }
    
    println(wordsCalculated+" words calculated")
  }
}
