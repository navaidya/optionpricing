#Open Source Derivatives Pricing Library 
**Available and maintained under the MIT License**

*Copyright (c) 2013 Chetan Gadgil*

Well tested Open source Derivatives Pricing libraries available under the MIT License.
* This libraries are packaged as OSGi bundles. These have been tested on Apache Karaf (Felix) 2.3.0.
* All versions of this set of libraries will ALWAYS be available and maintained under the MIT License.
* The libraries are well tested even in its 0.0.1 version. Each major function has at least one test case.

## Downloads
In order to download, you need to point your Maven repository to:
https://github.com/cgadgil/optionpricing/tree/master/cgoptpricing/releases

## Details
The set of libraries has been in implemented in Scala 2.10.1. However, it can be used from Java.

## Organization of libraries


1. **cgoptpricing** at https://github.com/cgadgil/optionpricing/tree/master/cgoptpricing
This is the base derivatives pricing library. These are the goals of release 0.5 (July 2013)
  + Have pricers available for 
    - Black Scholes (basic - does anyone even use Black-Scholes ;)), 
    - American options
    - Options on Options
    - Options on futures
    - Options on indexes
    - Binary options
    - Ratchet options
    - Time Switch Options
    - Currency-translated options
    - Interest rate options.
  + Have calculators for the Greeks of the options
    - Delta
    - Elasticity
    - Gamma
    - Vega
    - Theta
    - Rho
    - Cost of carry (sensitivity)
1. **finance.portfolio** at https://github.com/cgadgil/optionpricing/tree/master/finance.portfolio
This is the portfolio modeling library. This allows you to quickly create Back-testing scenarios against historical market data.
The library is quite powerful and includes a *domain specific language parser* for modeling scenarios. Market data is dynamically pulled in from public sources (MSN, Yahoo/Google finance).
For example to backtest the return of an equities portfolio composed of several long or short equities positions:
      portfolio pf1
      long THRM USD 10000 2010-12-14 at 11.14
      long LKQ USD 10000  2010-12-14 at 23.20
      long DORM USD 10000 2010-12-14 at 38.86
      short CWEI USD 10000 2010-12-14 at 12.91
      short TEN USD 10000 2010-12-14 at 41.19
Providing the above portfolio specification as a string to the scenario tester will produce a report of the returns of the portfolio over a given period of time.
This allows a very easy way to document the scenarios and use the same specification in the documentation as well as the actual test code.
1. **openoptions** at https://github.com/cgadgil/optionpricing/tree/master/openoptions
RESTful API and visualization tools. The goal of this sub-project is to make all the above APIs as a scalable grid of RESTful APIs. The market data feeds, parsers and the pricers will be tied to a high speed messaging bus (Apollo).
This project is being implemented in Scala 2.10.1 with the Play framework.


