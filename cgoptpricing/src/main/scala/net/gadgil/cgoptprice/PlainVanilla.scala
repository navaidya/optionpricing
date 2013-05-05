package net.gadgil.cgoptprice

object PlainVanilla {
  /**
   * Calculates the adjusted stock price after dividend deduction
   * @param dividends - list of tuples containing the dividend time and value respectively
   */
  def adjustedStockPriceAfterDividendDeduction(stockPrice: Double, discountRate: Double, dividends: List[(Double, Double)]) = {
    val theDiscountedDividends = dividends.map { theDividend =>
      math.exp(-discountRate * theDividend._1) * theDividend._2
    }
    theDiscountedDividends.foldLeft(stockPrice) { (theTotal, theValue) =>
      theTotal - theValue
    }
  }
}
