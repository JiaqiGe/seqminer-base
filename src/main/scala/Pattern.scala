

/**
 * Created by jiaqige on 4/1/15.
 */
class Pattern(var pattern:String) extends Serializable{

  def getPattern()={
    pattern
  }

  def isSeqExtend() = {
    var index = pattern.size-1
    var isFound = false

    while(index >= 0 && !isFound){
      if(pattern.charAt(index) == ',' || pattern.charAt(index) == '|'){
        isFound = true
      }
      index -= 1
    }

    pattern.charAt(index+1) == '|'
  }


  def contains(a:Char):Boolean = {
    pattern.contains(a)
  }

  def removeFirst():Pattern = {
    var index = 0
    var isFound = false

    while(index < pattern.size && !isFound){
      if(pattern.charAt(index) == '|' || pattern.charAt(index) == ','){
        isFound = true
      }
      index += 1
    }

    if(!isFound)
      return new Pattern("")

    return new Pattern(pattern.substring(index))

  }

  def removeLast():Pattern = {
    var index = pattern.size-1;
    var isFound = false

    while(index >= 0 && !isFound){
      if(pattern.charAt(index) == '|' || pattern.charAt(index) == ','){
        isFound = true
      }
      index -= 1
    }

    if(!isFound)
      return new Pattern("")

    return new Pattern(pattern.substring(0,index+1))
  }


  def itemJoin(other:Pattern):Pattern = {
    val pattern2 = other.pattern

    assert(this.removeFirst().pattern == "")
    assert(other.removeLast().pattern == "")

    if(pattern.compareTo(pattern2) < 0){
      return new Pattern(pattern+","+pattern2)
    }else{
      return null
    }
  }

  /**
   * join two patterns
   * @param other
   * @return
   */
  def seqJoin(other:Pattern):Pattern = {

    assert(this.removeFirst().pattern.equals(other.removeLast().pattern))

    val pattern2 = other.pattern

    var isFound = false
    var index = pattern2.size-1

    while(index >= 0 && !isFound){
      if(pattern2.charAt(index) == '|' || pattern2.charAt(index) == ',')
        isFound = true

      index -= 1
    }

    if(index >= 0)
      return new Pattern(pattern+pattern2.substring(index+1))
    else
      return new Pattern(pattern+"|"+pattern2)
  }

   def compareTo(other:Pattern):Boolean = {

    this.pattern.equals(other.getPattern())
  }

  override def toString(): String ={
    pattern
  }

  override def hashCode():Int={
    pattern.hashCode
  }


  override def equals(obj:Any):Boolean = {
    if(obj.isInstanceOf[Pattern]){
      this.pattern.equals(obj.asInstanceOf[Pattern].getPattern())
    }else
      false
  }


}

object Pattern{

  def main(args:Array[String]) = {
    val p = new Pattern("1,2|3,4|5")
    println(p.removeFirst())
    println(p.removeLast())
  }
}
