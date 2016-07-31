import java.io.Serializable

/**
 * Created by jiaqige on 4/1/15.
 */
class Occurrence(val tid:Double, val Pc:Double, val Pi:Double) extends Serializable {

  def getTid():Double = {
    tid
  }

  def getPatternProb() = {
    Pc
  }

  def getItemProb() = {
    Pi
  }

}

object Occurrence {

}
