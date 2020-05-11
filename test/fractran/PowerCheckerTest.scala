package fractran

import fractions.Fraction

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PowerCheckerTest {

  @Test def testBaseIsValidEndingFraction(): Unit = {
    println("isValidEndingFraction")
    val base = new Fraction(28, 1)
    val checker = new PowerChecker(base)
    val assertionMessage = ("The number " + base.toString
      + " should be recognized as a valid ending fraction for power checker based on "
      + base.toString)
    assert(checker.isValidEndingFraction(base), assertionMessage)
  }

  @Test def testOneIsValidEndingFraction(): Unit = {
    val base = new Fraction(28, 1)
    val checker = new PowerChecker(base)
    val one = new Fraction(1, 1)
    val assertionMessage = ("The number " + one.toString
      + " should be recognized as a valid ending fraction for power checker based on "
      + base.toString)
    assert(checker.isValidEndingFraction(one), assertionMessage)
  }

  @Test def testReciprocalOfBaseIsValidEndingFraction(): Unit = {
    val base = new Fraction(28, 1)
    val checker = new PowerChecker(base)
    val recip = base.reciprocal
    val assertionMessage = ("The number " + recip.toString
      + " should be recognized as a valid ending fraction for power checker based on "
      + base.toString)
    assert(checker.isValidEndingFraction(recip), assertionMessage)
  }

  @Test def testNineIsValidEndingFractionForOneQuarterPowerChecker(): Unit = {
    val base = new Fraction(1, 4)
    val checker = new PowerChecker(base)
    val fraction = new Fraction(9, 1)
    val assertionMessage = ("The number " + fraction.toString
      + " should be recognized as a valid ending fraction for power checker based on "
      + base.toString)
    assert(checker.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidEndingFraction(): Unit = {
    val base = new Fraction(3, 4)
    val checker = new PowerChecker(base)
    val fraction = new Fraction(9, 8)
    val assertionMessage = ("The number " + fraction.toString
      + " should not be recognized as a valid ending fraction for power checker based on "
      + base.toString)
    assert(!checker.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testNextStepAfterEight(): Unit = {
    val eight = new Fraction(8, 1)
    val powerOfTwoChecker = new PowerChecker(2)
    val expected = new Fraction(4, 1)
    val actual = powerOfTwoChecker.nextStep(eight)
    assertEquals(expected, actual)
  }

  @Test def testNextStepAfterSixteenth(): Unit = {
    val sixteenth = new Fraction(1, 16)
    val powerOfTwoChecker = new PowerChecker(2)
    val expected = new Fraction(1, 8)
    val actual = powerOfTwoChecker.nextStep(sixteenth)
    assertEquals(expected, actual)
  }

  @Test def testInterpret243AsPowerOfThree(): Unit = {
    val twoHundredFortyThree = new Fraction(243, 1)
    val powerOfThreeChecker = new PowerChecker(3)
    val assertionMessage = ("The number " + twoHundredFortyThree.toString
      + " should be recognized as a power of 3")
    assert(powerOfThreeChecker.interpret(powerOfThreeChecker.run(twoHundredFortyThree)),
      assertionMessage)
  }

  @Test def testInterpretSixteenthAsPowerOfFour(): Unit = {
    val sixteenth = new Fraction(1, 16)
    val powerOfFourChecker = new PowerChecker(4)
    val assertionMessage = ("The number " + sixteenth.toString
      + " should be recognized as a power of 4")
    assert(powerOfFourChecker.interpret(powerOfFourChecker.run(sixteenth)),
      assertionMessage)
  }

  @Test def testDoNotInterpret244AsPowerOfThree(): Unit = {
    val twoHundredFortyFour = new Fraction(244, 1)
    val powerOfThreeChecker = new PowerChecker(3)
    val assertionMessage = ("The number " + twoHundredFortyFour.toString
      + " should not be recognized as a power of 3")
    assert(!powerOfThreeChecker.interpret(powerOfThreeChecker.run(twoHundredFortyFour)),
      assertionMessage)
  }

  @Test def testDoNotInterpretTen25thsAsPowerOfFive(): Unit = {
    val ten25ths = new Fraction(10, 25)
    val powerOfFiveChecker = new PowerChecker(5)
    val assertionMessage = ("The number " + ten25ths.toString
      + " should not be recognized as a power of 5")
    assert(!powerOfFiveChecker.interpret(powerOfFiveChecker.run(ten25ths)),
      assertionMessage)
  }

  @Test def testDoNotInterpret128AsPowerOfTen(): Unit = {
    val one28 = new Fraction(128, 1)
    val powerOfTenChecker = new PowerChecker(10)
    val assertionMessage = ("The number " + one28.toString
      + " should not be recognized as a power of 10")
    assert(!powerOfTenChecker.interpret(powerOfTenChecker.run(one28)),
      assertionMessage)
  }

  @Test def testNoPowerCheckerForZero(): Unit = {
    val zero = new Fraction(0, 1)
    try {
      val zeroPowerChecker = new PowerChecker(zero)
      val failMsg = "Should not have been able to create " + zeroPowerChecker.toString + " for 0"
      fail(failMsg)
    } catch {
      case iae: IllegalArgumentException =>
        println("Trying to make PowerChecker for 0 correctly caused IllegalArgumentException")
        println("\"" + iae.getMessage + "\"")
      case ae: ArithmeticException =>
        println("ArithmeticException is adequate for PowerChecker for 0")
        println("\"" + ae.getMessage + "\"")
      case e: Exception => val failMsg = (e.getClass.getName
        + " is the wrong exception to throw for trying to make PowerChecker for 0")
        fail(failMsg)
    }
  }

}
