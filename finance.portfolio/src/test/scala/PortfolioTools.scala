package net.gadgil.finance.portfolio

import org.specs2.mutable._

class StubSpec extends Specification {

  "portfolio parser" should {
    val theDefinition = """
      portfolio pf1
      long CSCO $ 100.3 2008-02-02
      long MSFT $ 22.3  2008-02-02
      short GE $ 15.3 2008-02-02
        """
    "parse test" in {
      val x = PortfolioDefinition.parseAll(PortfolioDefinition.portfolioStruct, theDefinition)
      println(x)
    }
  }
}
