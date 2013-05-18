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
  def position: Parser[Any] = ("long" | "short") ~ ident ~ decimalNumber ~ ("shares" | "dollars") ~ onDate1 ^^ {
    case positionType ~ symbol ~ numShares ~ sharesOrDollars ~ theDate => { println(positionType, symbol, numShares, sharesOrDollars, theDate) }
  }
  def onDate1: Parser[List[String]] = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})") ^^ { nim =>
    val r = new Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    val y = List("DD")
    val x = r.findFirstMatchIn(nim).fold(y){xxx => xxx.subgroups}
    println(x.toList)
    //nim
    x
  }
}
