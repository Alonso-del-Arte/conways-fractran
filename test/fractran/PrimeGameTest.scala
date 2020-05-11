package fractran

import fractions.Fraction

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PrimeGameTest {

  @Test def testIsValidStartingFraction(): Unit = {
    println("isValidStartingFraction")
    val fraction = new Fraction(7, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid starting fraction")
    assert(PrimeGame.isValidStartingFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidStartingFraction(): Unit = {
    val fraction = new Fraction(7, 2)
    val assertionMessage = ("The fraction " + fraction.toString
      + " should not be recognized as a valid starting fraction")
    assert(!PrimeGame.isValidStartingFraction(fraction), assertionMessage)
  }

  @Test def testIsValidStateFraction(): Unit = {
    println("isValidStateFraction")
    val fraction = new Fraction(9, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid state fraction")
    assert(PrimeGame.isValidStateFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidStateFraction(): Unit = {
    val fraction = new Fraction(9, 2)
    val assertionMessage = ("The fraction " + fraction.toString
      + " should not be recognized as a valid state fraction")
    assert(!PrimeGame.isValidStateFraction(fraction), assertionMessage)
  }

  @Test def testIsValidEndingFraction(): Unit = {
    println("isValidEndingFraction")
    val fraction = new Fraction(8, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid ending fraction")
    assert(PrimeGame.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidEndingFraction(): Unit = {
    val fraction = new Fraction(24, 1)
    val assertionMessage = ("The number " + fraction.toString
      + " should not be recognized as a valid ending fraction")
    assert(!PrimeGame.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testInvalidStep(): Unit = {
    val badCurrentStep = new Fraction(8, 3)
    try {
      val result = PrimeGame.nextStep(badCurrentStep)
      val failMsg = ("Using fraction " + badCurrentStep.toString
        + " should have caused an exception, not given result "
        + result.toString)
      fail(failMsg)
    } catch {
      case iae: IllegalArgumentException => println("Trying to use fraction "
        + badCurrentStep.toString
        + " correctly caused IllegalArgumentException")
        println("\"" + iae.getMessage + "\"")
      case re: RuntimeException => val failMsg = (re.getClass.getName
        + " is the wrong exception to throw for trying to use fraction "
        + badCurrentStep.toString)
        fail(failMsg)
    }
  }

  @Test def testNextStep(): Unit = {
    println("nextStep")
    val currentStep = new Fraction(290, 1)
    val expected = new Fraction(770, 1)
    val actual = PrimeGame.nextStep(currentStep)
    assertEquals(expected, actual)
  }

  @Test def testInterpret(): Unit = {
    println("interpret")
    val result = new Fraction(32, 1)
    val expected = 5
    val actual = PrimeGame.interpret(result)
    assertEquals(expected, actual)
  }

  @Test def testDoNotInterpret(): Unit = {
    val result = new Fraction(32, 65)
    try {
      val interpretation = PrimeGame.interpret(result)
      val failMsg = ("Trying to interpret " + result.toString
        + " should have caused an exception, not given interpretation "
        + interpretation.toString)
      fail(failMsg)
    } catch {
      case iae: IllegalArgumentException => println("Trying to interpret "
        + result.toString + " correctly caused IllegalArgumentException")
        println("\"" + iae.getMessage + "\"")
      case re: RuntimeException => val failMsg = (re.getClass.getName
        + " is the wrong exception to throw for trying to interpret "
        + result.toString)
        fail(failMsg)
    }
  }

}
