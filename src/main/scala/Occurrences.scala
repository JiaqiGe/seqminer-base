

/**
 * Created by jiaqige on 4/1/15.
 */
class Occurrences(val candidate:Pattern, val occurrences: List[Occurrence]) extends Serializable{

  def getCandidate():Pattern={
    candidate
  }

  def getListOfOccurrence():List[Occurrence]={
    occurrences
  }

  def getSupport():Double = {
    computeSupportProbability()
  }


  /**
   * compute the support probability
   */
  def computeSupportProbability() = {
    occurrences.sortBy(x=>x.tid)
    var support:Double = 0;
    var notSupport:Double = 1;

    for(i <- 0 until occurrences.length){
      val pr = occurrences(i).Pc
      support = support + pr*notSupport
      notSupport = notSupport * (1 - pr)
    }

    support
  }
}
