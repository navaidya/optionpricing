#Open source Derivatives Pricing Library

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


### cgoptpricing at https://github.com/cgadgil/optionpricing/tree/master/cgoptpricing
  
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


### finance.portfolio at https://github.com/cgadgil/optionpricing/tree/master/finance.portfolio

This is the portfolio modeling library.


* https://github.com/cgadgil/optionpricing/tree/master/openoptions
 

