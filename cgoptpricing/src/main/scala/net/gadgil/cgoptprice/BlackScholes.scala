package net.gadgil.cgoptprice

import org.apache.commons.math3.distribution.NormalDistribution

object BlackScholes {

  private def d1d2(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    riskFreeRate: Double,
    volatility: Double) = {
    val d1 = (math.log(stockPrice / strikePrice) +
      (riskFreeRate + math.pow(volatility, 2) / 2) * timeToExpiry) / (volatility * math.sqrt(timeToExpiry))
    //Console.println(d1)
    val d2 = d1 - volatility * math.sqrt(timeToExpiry)
    (d1, d2)
  }

  def callOption(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    riskFreeRate: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, riskFreeRate, volatility)
    stockPrice * CND(d1) -
      strikePrice * math.exp(-riskFreeRate * timeToExpiry) * CND(d2)
  }

  def putOption(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    riskFreeRate: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, riskFreeRate, volatility)
    strikePrice * math.exp(-riskFreeRate * timeToExpiry) * CND(-d2) - stockPrice * CND(-d1)
  }

  def callPrice(strikePrice: Double, riskFreeRate: Double, timeToExpiry: Double, d1: Double, d2: Double) = {
    val x = (1, 2, 3, 4)
  }

  def CND(X: Double) = {
    val theNormalDistribution = new NormalDistribution
    theNormalDistribution.cumulativeProbability(X)
  }

  // Approximation of the cumulative normal distribution function 
  def CNDApprox(X: Double) = {
    val a1 = 0.31938153
    val a2 = -0.356563782
    val a3 = 1.781477937
    val a4 = -1.821255978
    val a5 = 1.330274429

    val L = Math.abs(X);
    val K = 1.0 / (1.0 + 0.2316419 * L);
    var w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-L * L / 2) * (a1 * K + a2 * K * K + a3
      * Math.pow(K, 3) + a4 * Math.pow(K, 4) + a5 * Math.pow(K, 5))

    if (X < 0.0) {
      w = 1.0 - w
    }
    w
  }

}

