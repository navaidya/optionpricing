package net.gadgil.finance.portfolio

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.matching.Regex
import org.joda.time.DateTime

class PortfolioDefinition {

}

object PortfolioDefinition extends JavaTokenParsers {
  def portfolioStruct: Parser[PortfolioInfo] = "portfolio" ~> ident ~ portfolioComposition ^^ {
    case portfolioName ~ portfolioComposition =>
      PortfolioInfo(portfolioName, portfolioComposition)
  }
  def portfolioComposition: Parser[Seq[PortfolioPosition]] = position.+
  def position: Parser[PortfolioPosition] = ("long" | "short") ~ ident ~ "$" ~ decimalNumber ~ onDate1 ^^ {
    case positionType ~ symbol ~ dollars ~ amount ~ theDate => {
      //println(positionType, symbol, amount, theDate)
      PortfolioPosition(positionType, symbol, amount.toDouble, theDate)
    }
  }
  def onDate1: Parser[DateTime] = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})") ^^ { theDate =>
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
    
  }
  
  def parsePortfolio(portfolioDefinitionString: String): PortfolioInfo = {
    PortfolioDefinition.parseAll(PortfolioDefinition.portfolioStruct, portfolioDefinitionString).get
  }
}