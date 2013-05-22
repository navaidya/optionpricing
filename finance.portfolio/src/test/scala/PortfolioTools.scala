package net.gadgil.finance.portfolio

import org.specs2.mutable._

class PortfolioToolsSpecification extends Specification {

  "portfolio parser" should {
    val theDefinition = """
      portfolio pf1
      long THRM USD 10000 on 2010-12-14 at 11.14
      long LKQ USD 10000  on 2010-12-14 at 23.20 stop loss 50%
      long DORM USD 10000 on 2010-12-14 at 38.86
      short CWEI USD 10000 on 2010-12-14 at 12.91 stop loss 100%
      short TEN USD 10000 on 2010-12-14 at 41.19  stop loss 100%
        """
    "parse test" in {
      val x = PortfolioDefinition.parseAll(PortfolioDefinition.portfolioStruct, theDefinition)
      println(x)
      x.get.name must be equalTo "pf1"
      x.get.positions(0).symbol must be equalTo "THRM"
    }
  }
}
