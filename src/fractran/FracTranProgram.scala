package fractran

import fractions.Fraction

/**
 * Describes a FracTran program.
 */
abstract class FracTranProgram {

  val INITIAL_NUMBER: Fraction

  val FRACTIONS: List[Fraction]

  def isValidStartingFraction(fraction: Fraction): Boolean

  def isValidStateFraction(fraction: Fraction): Boolean
    = fraction.denominator == 1L

  def isValidEndingFraction(fraction: Fraction): Boolean

  def nextStep(current: Fraction): Fraction = {
    if (!this.isValidStateFraction(current)) {
      val excMsg = "Fraction " + current.toString + " is not a valid state fraction"
      throw new IllegalArgumentException(excMsg)
    }
    val next = this.FRACTIONS.find(f => { this.isValidStateFraction(f * current) })
    try {
      current * next.get
    } catch {
      case nsee: NoSuchElementException => throw
        new NoValidNextStepException("No valid next step after "
          + current.toString, nsee, current)
    }
  }

}
