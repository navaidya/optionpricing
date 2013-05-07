package net.gadgil.cgoptprice

import org.apache.commons.math3.distribution.NormalDistribution

object BlackScholes {

  private def d1d2(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    discountRate: Double,
    volatility: Double) = {
    val d1 = (math.log(stockPrice / strikePrice) +
      (discountRate + math.pow(volatility, 2) / 2) * timeToExpiry) / (volatility * math.sqrt(timeToExpiry))
    //Console.println(d1)
    val d2 = d1 - volatility * math.sqrt(timeToExpiry)
    (d1, d2)
  }

  /**
   * Black Scholes call option price
   * @param stockPrice - current price of the underlying stock
   * @param strikePrice - option strike price
   * @param timeToExpiry - option time to expiry
   * @param discoutRate - the discount rate. Normally the risk free rate
   * @param volatility - the volatility of the price
   */
  def callOption(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    discountRate: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, discountRate, volatility)
    stockPrice * CND(d1) -
      strikePrice * math.exp(-discountRate * timeToExpiry) * CND(d2)
  }

  /**
   * Black Scholes put option price
   * @param stockPrice - current price of the underlying stock
   * @param strikePrice - option strike price
   * @param timeToExpiry - option time to expiry
   * @param discoutRate - the discount rate. Normally the risk free rate
   * @param volatility - the volatility of the price
   */
  def putOption(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    discountRate: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, discountRate, volatility)
    strikePrice * math.exp(-discountRate * timeToExpiry) * CND(-d2) - stockPrice * CND(-d1)
  }

  /**
   * Generalized Black Scholes European call option paying a continuous dividend yield
   * Can be used to price European options on stocks with continuous dividend yield, options on futures and currency options
   * @param costOfCarry - Cost of carry
   * 	set this to discountRate for regular Black-Scholes price
   *    set this to (discountRate - dividendYield) - for stocks with continuous dividend yield
   *    set this to 0 for the Black-Scholes futures option
   *    set this to (discountRate - discountRateInForeignCurrency) for currency option
   */
  def callOptionGeneralizedBlackScholes(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    discountRate: Double,
    costOfCarry: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, costOfCarry, volatility)
    stockPrice * math.exp((costOfCarry - discountRate) * timeToExpiry) * CND(d1) - strikePrice * math.exp(-discountRate * timeToExpiry) * CND(d2)
  }

  /**
   * Generalized Black Scholes European put option paying a continuous dividend yield
   * Can be used to price European options on stocks with continuous dividend yield, options on futures and currency options
   * @param costOfCarry - Cost of carry
   * 	set this to discountRate for regular Black-Scholes price
   *    set this to (discountRate - dividendYield) - for stocks with continuous dividend yield
   *    set this to 0 for the Black-Scholes futures option
   *    set this to (discountRate - discountRateInForeignCurrency) for currency option
   */
  def putOptionGeneralizedBlackScholes(stockPrice: Double,
    strikePrice: Double,
    timeToExpiry: Double,
    discountRate: Double,
    costOfCarry: Double,
    volatility: Double) = {
    val (d1, d2) = d1d2(stockPrice, strikePrice, timeToExpiry, costOfCarry, volatility)
    strikePrice * math.exp(-discountRate * timeToExpiry) * CND(-d2) - stockPrice * math.exp((costOfCarry - discountRate) * timeToExpiry) * CND(-d1)
  }

  /**
   * Cumulative normal distribution value
   */
  def CND(X: Double) = {
    val theNormalDistribution = new NormalDistribution
    theNormalDistribution.cumulativeProbability(X)
  }

  /**
   * fast approximation of the cumulative normal distribution function
   */
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

