import java.text.DecimalFormat

/**
 * Created by jiaqige on 4/2/15.
 */
class Rule(val name:String, val support:Double, val confidence:Double, val lift:Double) extends  Serializable{

  def toRuleString() = {
    val df = new DecimalFormat("#0.00")

    name +";"+df.format(support)+";"+df.format(confidence)+";"+df.format(lift)
  }
}
