package fractran

import fractions.Fraction

import org.junit.jupiter.api.Test

class KilminsterPrimeTest {

  @Test def testIsValidEndingFraction(): Unit = {
    println("isValidEndingFraction")
    val oneHundred = new Fraction(100, 1)
    val assertionMessage = ("The number " + oneHundred.toString
      + " should be recognized as a valid ending fraction for Kilminster prime program")
    assert(KilminsterPrime.isValidEndingFraction(oneHundred), assertionMessage)
  }

  @Test def testIsNotValidEndingFraction(): Unit = {
    val oneHundredTwentyEight = new Fraction(128, 1)
    val assertionMessage = ("The number " + oneHundredTwentyEight.toString
      + " should not be recognized as a valid ending fraction for Kilminster prime program")
    assert(!KilminsterPrime.isValidEndingFraction(oneHundredTwentyEight),
      assertionMessage)
  }

}
