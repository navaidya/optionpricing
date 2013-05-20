package net.gadgil.finance.portfolio

import org.specs2.mutable._

class PortfolioToolsSpecification extends Specification {

  "portfolio parser" should {
    val theDefinition = """
      portfolio pf1
      long CSCO USD 1000 2008-02-02
      long MSFT USD 1000  2008-02-02
      short GE USD 1000 2008-02-02
        """
    "parse test" in {
      val x = PortfolioDefinition.parseAll(PortfolioDefinition.portfolioStruct, theDefinition)
      println(x)
      x.get.name must be equalTo "pf1"
      x.get.positions(0).symbol must be equalTo "CSCO"
    }
  }
}
