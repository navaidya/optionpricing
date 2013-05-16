package net.gadgil.finance.portfolio

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.matching.Regex

class PortfolioDefinition {

}

case class TradeDate(year: Int, month: Int, day: Int)

object PortfolioDefinition extends JavaTokenParsers {
  def portfolioStruct: Parser[Any] = "portfolio" ~> ident ~ portfolioComposition ^^ {
    case portfolioName ~ portfolioComposition =>
      ""
  }
  def portfolioComposition: Parser[Any] = position.+
  def position: Parser[Any] = ("long" | "short") ~ ident ~ decimalNumber ~ onDate1 ^^ {
    case positionType ~ symbol ~ numShares ~ theDate => { println(positionType, symbol, numShares, theDate) }
  }
  def onDate1: Parser[String] = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})") ^^ { nim =>
    println(nim)
    nim
  }
}
