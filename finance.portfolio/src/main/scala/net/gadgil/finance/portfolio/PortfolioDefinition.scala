package net.gadgil.finance.portfolio

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.matching.Regex
import org.joda.time.DateTime

class PortfolioDefinition {

}

object PortfolioDefinition extends JavaTokenParsers {
  def portfolioStruct: Parser[Any] = "portfolio" ~> ident ~ portfolioComposition ^^ {
    case portfolioName ~ portfolioComposition =>
      ""
  }
  def portfolioComposition: Parser[Any] = position.+
  def position: Parser[Any] = ("long" | "short") ~ ident ~ "$" ~ decimalNumber ~ onDate1 ^^ {
    case positionType ~ symbol ~ dollars ~ amount ~ theDate => { println(positionType, symbol, amount, theDate) }
  }
  def onDate1: Parser[DateTime] = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})") ^^ { theDate =>
    val r = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    val y = List("DD")
    val theMatchedGroups = r.findFirstMatchIn(theDate).fold(y){xxx => xxx.subgroups}
    //println(x.toList)
    //nim
    //theMatchedGroups
    //val x = new DateTime()
    new DateTime(theMatchedGroups(0).toInt, theMatchedGroups(1).toInt, theMatchedGroups(2).toInt, 0,0 )
    //x
  }
}

case class TradeDate(year: Int, month: Int, day: Int)
case class PortfolioInfo(name: String, positions: Seq[PortfolioPosition])
case class PortfolioPosition(symbol: String, numShares: Double)