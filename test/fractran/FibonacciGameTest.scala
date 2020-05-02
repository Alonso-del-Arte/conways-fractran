package fractran

import fractions.Fraction

import java.util.NoSuchElementException

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class FibonacciGameTest {

  @Test def testIsValidStartingFraction(): Unit = {
    println("isValidStartingFraction")
    val fraction = new Fraction(1950, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid starting fraction")
    assert(FibonacciGame.isValidStartingFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidStartingFraction(): Unit = {
    val number = new Fraction(79, 1)
    val assertionMessage = ("The whole number " + number.toString
      + " should not be recognized as a valid starting number "
      + "because it's not of the form 78 * 5^n")
    assert(!FibonacciGame.isValidStartingFraction(number), assertionMessage)
  }

  @Test def testIsValidStateFraction(): Unit = {
    println("isValidStateFraction")
    val fraction = new Fraction(9, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid state fraction")
    assert(FibonacciGame.isValidStateFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidStateFraction(): Unit = {
    val fraction = new Fraction(9, 2)
    val assertionMessage = ("The fraction " + fraction.toString
      + " should not be recognized as a valid state fraction")
    assert(!FibonacciGame.isValidStateFraction(fraction), assertionMessage)
  }

  @Test def testIsValidEndingFraction(): Unit = {
    println("isValidEndingFraction")
    val fraction = new Fraction(32, 1)
    val assertionMessage = ("Whole number " + fraction.toString
      + " should be recognized as a valid ending fraction")
    assert(FibonacciGame.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testIsNotValidEndingFraction(): Unit = {
    val fraction = new Fraction(24, 1)
    val assertionMessage = ("The number " + fraction.toString
      + " should not be recognized as a valid ending fraction")
    assert(!FibonacciGame.isValidEndingFraction(fraction), assertionMessage)
  }

  @Test def testInvalidStep(): Unit = {
    val badCurrentStep = new Fraction(8, 3)
    try {
      val result = FibonacciGame.nextStep(badCurrentStep)
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
    val expected = new Fraction(230, 1)
    val actual = FibonacciGame.nextStep(currentStep)
    assertEquals(expected, actual)
  }

  @Test def testNoNextStepPossible(): Unit = {
    val haltingNumber = new Fraction(10, 1)
    try {
      val result = FibonacciGame.nextStep(haltingNumber)
      val failMsg = ("Trying to get next step after " + haltingNumber.toString
        + " should have caused an exception, not given result "
        + result.toString)
      fail(failMsg)
    } catch {
      case nvnse: NoValidNextStepException => println("Trying to get next step after "
        + haltingNumber.toString + " correctly caused NoValidNextStepException")
        println("\"" + nvnse.getMessage + "\"")
        val wrappedException = nvnse.getCause
        println("Wrapped exception is of type "
          + wrappedException.getClass.getName)
        println("\"" + wrappedException.getMessage + "\"")
        val assertionMessage =
          "NoValidNextStepException should wrap NoSuchElementException"
        assert(wrappedException.isInstanceOf[NoSuchElementException],
          assertionMessage)
      case nsee: NoSuchElementException => println("\"" + nsee.getMessage + "\"")
        val failMsg =
          "NoSuchElementException should have been wrapped in a NoValidNextStepException"
        fail(failMsg)
      case re: RuntimeException => val failMsg = (re.getClass.getName
        + " is the wrong exception to throw for trying to get next step after "
        + haltingNumber.toString)
        fail(failMsg)
    }
  }

}
