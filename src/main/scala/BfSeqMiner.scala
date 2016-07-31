import java.io.{FileInputStream, File}
import java.net.URI
import java.util.Properties

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}
import org.apache.log4j.Logger
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by jiaqige on 4/26/15.
 */
class BfSeqMiner {

}

object BfSeqMiner {
  val logger = Logger.getLogger(this.getClass)

  def main(args: Array[String]) = {
    if (args == null || args.length < 2) {
      logger.error("not enough parameters!\n Usage:<inputPath><outputPath>")
      System.exit(-1)
    }
    val inputPath = args(0)
    val outputPath = args(1)

    var support = 1000
    var minSplit = 50
    var maxItr = 4
    var itemProb = 0.8

    for (i <- 2 until args.length) {
      val opt = args(i)

      if (opt.startsWith("--minSup=")) {
        support = opt.stripPrefix("--minSup=").toInt
      }

      if (opt.startsWith("--minSplit=")) {
        minSplit = opt.stripPrefix("--minSplit=").toInt
      }

      if (opt.startsWith("--maxItr=")) {
        maxItr = opt.stripPrefix("--maxItr=").toInt
      }

      if (opt.startsWith("--extProb=")) {
        itemProb = opt.stripPrefix("--extProb=").toDouble
      }
    }



    // configure the spark context
    //todo: add jar from property
    val conf = new SparkConf().setAppName("SPM")
      .setMaster("local")

    val sc = new SparkContext(conf)


    //read data to RDD and transform it to the vertical format

    val seqMiner = new SeqMiner(support, 0.1, 1, Integer.MAX_VALUE)

    val data = seqMiner.readSeqFile(sc, inputPath, itemProb, minSplit)

    //mine sequential patterns with quality measurements
    val frequentPatternsRDD = seqMiner.seqMine(sc, data, maxItr)

    //save output to hdfs

    if (frequentPatternsRDD != null) {
      frequentPatternsRDD.repartition(1).saveAsTextFile(outputPath)

      //generate rules
      //val rules = seqMiner.buildRules(frequentPatternsRDD)
      //println(rules)
    }
  }
}