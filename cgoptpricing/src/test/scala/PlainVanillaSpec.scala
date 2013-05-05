package net.gadgil

import org.specs2.mutable._
import net.gadgil.cgoptprice.BlackScholes

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
  }

}