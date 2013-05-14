package net.gadgil

import org.specs2.mutable._
import net.gadgil.cgoptprice.BlackScholes
import net.gadgil.cgoptprice.PlainVanilla
import net.gadgil.cgoptprice.OptionSensitivities

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
      val x = BlackScholes.callOptionGeneralizedBlackScholes(60, 65, 0.25, 0.08, 0.08, 0.3)
      math.abs(2.1334 - x) must be lessThan 0.0001
    }

    "generic black-scholes put" in {
      val x = BlackScholes.putOptionGeneralizedBlackScholes(75, 70, 0.5, 0.1, 0.05, 0.35)
      math.abs(4.0870 - x) must be lessThan 0.0001
    }
    
    "distributions" in {
      val theNPrime = OptionSensitivities.nprime(0.2387)
      math.abs(0.3877 - theNPrime) must be lessThan 0.0001
      math.abs(0.3975 - OptionSensitivities.nprime(0.0837)) must be lessThan 0.0001
    }
    
    "Sensitivities test" in {
      val (stockPrice, strikePrice, timeToExpiry, discountRate, costOfCarry, volatility) = (105.0, 100.0, 0.5, 0.1, 0.0, 0.36)
      val theDeltaCall = OptionSensitivities.deltaCall(stockPrice, strikePrice, timeToExpiry, discountRate, costOfCarry, volatility)
      val theDeltaPut = OptionSensitivities.deltaPut(stockPrice, strikePrice, timeToExpiry, discountRate, costOfCarry, volatility)
      math.abs(0.5946 - theDeltaCall) must be lessThan 0.0001
      math.abs(-0.3566 - theDeltaPut) must be lessThan 0.0001
      val theElasticityCall = OptionSensitivities.elasticityCall(stockPrice, strikePrice, timeToExpiry, discountRate, costOfCarry, volatility)
      val theElasticityPut = OptionSensitivities.elasticityPut(stockPrice, strikePrice, timeToExpiry, discountRate, costOfCarry, volatility)
      math.abs(-4.8775 - theElasticityPut) must be lessThan 0.0001
      val (stockPrice2, strikePrice2, timeToExpiry2, discountRate2, costOfCarry2, volatility2) = (55.0, 60.0, 0.75, 0.1, 0.1, 0.3)
      val theVegaCallPut = OptionSensitivities.vegaCallPut(stockPrice2, strikePrice2, timeToExpiry2, discountRate2, costOfCarry2, volatility2)
      math.abs(18.9358 - theVegaCallPut) must be lessThan 0.0001
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
