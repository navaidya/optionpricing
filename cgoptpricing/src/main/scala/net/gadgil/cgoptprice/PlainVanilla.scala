package net.gadgil.cgoptprice

object PlainVanilla {
  /**
   * Calculates the adjusted stock price after dividend deduction
   * @param dividends - list of tuples containing the dividend time and value respectively 
   */
	def adjustedStockPriceAfterDividendDeduction(stockPrice: Double, riskFreeRate: Double, dividends: List[(Double, Double)]) = {
	  val theDiscountedDividends = dividends.map { theDividend =>
	    math.exp(-riskFreeRate * theDividend._1) * theDividend._2
	  }
	  theDiscountedDividends.foldLeft(stockPrice) { (theTotal, theValue) =>
	    theTotal - theValue
	  }
	}
}
