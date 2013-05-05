package net.gadgil

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PlainVanillaTest extends Spec with ShouldMatchers {

  describe("Placeholder for JUnit based tests") {
    it("should run test cases") {
      "1" should equal("1")
    }
  }
}
