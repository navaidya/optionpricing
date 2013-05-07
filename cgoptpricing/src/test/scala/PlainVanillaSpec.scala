package net.gadgil

import org.specs2.mutable._
import net.gadgil.cgoptprice.BlackScholes
import net.gadgil.cgoptprice.PlainVanilla

class PlainVanillaSpec extends Specification {

  "Vanilla Options" should {
    "call option" in {
      val x = BlackScholes.callOption(60, 65, 0.25, 0.08, 0.3)
      //Console.println(x)
      math.abs(2.1334 - x) must be lessThan 0.0001
      //10 must equalTo(10)
    }

    "put option" in {
      val x = BlackScholes.putOption(65, 60, 0.25, 0.08, 0.3)
      //Console.println(x)
      math.abs(1.39866 - x) must be lessThan 0.0001
    }
    
    "generic black-scholes call" in {
      val x = BlackScholes.callOptionGeneralizedBlackScholes(60, 65, 0.25, 0.08, 0.0, 0.3)
      math.abs(2.1334 - x) must be lessThan 0.0001
    }

    "generic black-scholes put" in {
      val x = BlackScholes.putOptionGeneralizedBlackScholes(75, 70, 0.5, 0.1, 0.05, 0.35)
      math.abs(4.0870 - x) must be lessThan 0.0001
    }
  }
  
  "Stock Price Adjustments" should {
    "dividend adjustment" in {
      val theAdjustedPrice = PlainVanilla.adjustedStockPriceAfterDividendDeduction(100, 
          0.1, 
          List((0.25, 2.0), (0.5, 2.0)))
      math.abs(theAdjustedPrice - 96.1469) must be lessThan 0.0001
    }
  }

}
