package net.gadgil.finance.portfolio

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.matching.Regex
import org.joda.time.DateTime
import com.ning.http.client.AsyncHttpClient
import org.supercsv.io.CsvMapReader
import java.io.InputStreamReader
import org.supercsv.prefs.CsvPreference
import scala.collection.JavaConversions._

class PortfolioDefinition {

}

object PortfolioDefinition extends JavaTokenParsers {
  def portfolioStruct: Parser[PortfolioInfo] = "portfolio" ~> ident ~ portfolioComposition ^^ {
    case portfolioName ~ portfolioComposition =>
      PortfolioInfo(portfolioName, portfolioComposition)
  }
  def portfolioComposition: Parser[Seq[PortfolioPosition]] = position.+
  def position: Parser[PortfolioPosition] = ("long" | "short") ~ ident ~ ident ~ decimalNumber ~ onDate1 ~ stopLoss ^^ {
    case positionType ~ symbol ~ currencyCode ~ amount ~ theDate ~ theStopLoss => {
      //println(positionType, symbol, amount, theDate)
      PortfolioPosition(positionType, symbol, amount.toDouble, theDate)
    }
  }

  def stopLoss: Parser[Double] = "stop loss" ~> decimalNumber ~ "%".? ^^ {
    case theStopLoss ~ isPercent =>
      theStopLoss.toDouble * (isPercent.getOrElse("1")).toDouble
  }

  def onDate1: Parser[DateTime] = "on" ~> new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})") ^^ { theDate =>
    val r = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    val y = List("DD")
    val theMatchedGroups = r.findFirstMatchIn(theDate).fold(y) { xxx => xxx.subgroups }
    new DateTime(theMatchedGroups(0).toInt, theMatchedGroups(1).toInt, theMatchedGroups(2).toInt, 0, 0)
  }
}

case class TradeDate(year: Int, month: Int, day: Int)
case class PortfolioInfo(name: String, positions: Seq[PortfolioPosition])
case class PortfolioPosition(positionType: String, symbol: String, amount: Double, dateTime: DateTime)

object PortfolioProcessor {
  def getHistoricalPrices(symbol: String, fromDate: DateTime, toDate: DateTime = DateTime.now()) = {
    val theCurrentDate = new DateTime()
    // Example: http://finance.yahoo.com/q/hp?s=MSFT&a=01&b=3&c=2005&d=01&e=3&f=2013&g=m
    // http://ichart.finance.yahoo.com/table.csv?s=MSFT&a=1&b=4&c=2006&d=1&e=4&f=2013&g=m&ignore=.csv
    // Example: http://finance.yahoo.com/q/hp?s=<SYMBOL>&a=<FROM-MONTH-1>&b=<FROM-DAY>&c=<FROM-YEAR>&d=<TO-MONTH-1>&e=<TO-DAY>&f=<TO-YEAR>&g=m
    val theUrlTemplate = "http://ichart.finance.yahoo.com/table.csv?s=%s&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d&g=m&ignore=.csv"
    val theYahooHistoricalPriceUrl = theUrlTemplate.format(
      symbol,
      theCurrentDate.monthOfYear().get() - 1,
      theCurrentDate.dayOfMonth().get(),
      theCurrentDate.year().get() - 7,
      theCurrentDate.monthOfYear().get() - 1,
      theCurrentDate.dayOfMonth().get(),
      theCurrentDate.year().get())
    val ahc = new AsyncHttpClient
    val theQueryParameters: Map[String, String] = Map("s" -> symbol,
      "a" -> String.valueOf(fromDate.monthOfYear().get() - 1),
      "b" -> String.valueOf(fromDate.dayOfMonth().get()),
      "c" -> String.valueOf(fromDate.year().get()),
      "d" -> String.valueOf(toDate.monthOfYear().get() - 1),
      "e" -> String.valueOf(toDate.dayOfMonth().get()),
      "f" -> String.valueOf(toDate.year().get()))
    val theGetRequest = ahc.prepareGet(theYahooHistoricalPriceUrl)
    theQueryParameters.map { f => theGetRequest.addQueryParameter(f._1, f._2) }
    val theCSV = theGetRequest.execute().get().getResponseBodyAsStream()
    val theCSVReader = new CsvMapReader(new InputStreamReader(theCSV), CsvPreference.STANDARD_PREFERENCE)
    //val theHeader = theCSVReader.getHeader(true)
    //final String[] header = mapReader.getHeader(true);
    //final CellProcessor[] processors = getProcessors();

    //val x:Seq[Map[String, String]] = theCSVReader.
    class CsvIterator(csvMapReader: CsvMapReader) extends Iterator[Map[String, String]] {
      val header = csvMapReader.getHeader(true)
      var _next: Map[String, String] = null;
      def next = {
        _next
      }

      def hasNext = {
        val x = csvMapReader.read(header: _*)
        if (x == null) {
          _next = null
          false
        } else {
          _next = x.toMap
          true
        }
      }
    }
    //    val iterator = Iterator.continually(theCSVReader.read(theHeader: _*)).takeWhile(_ != null)
    //    val x = iterator.map { m =>
    //      m.toMap
    //    }
    val theIterator = new CsvIterator(theCSVReader)
    theIterator.toArray
  }

  def parsePortfolio(portfolioDefinitionString: String): PortfolioInfo = {
    PortfolioDefinition.parseAll(PortfolioDefinition.portfolioStruct, portfolioDefinitionString).get
  }
}