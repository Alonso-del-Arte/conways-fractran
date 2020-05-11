package fractran

import fractions.Fraction

/**
 * Describes a FracTran program.
 */
abstract class FracTranProgram {

  val INITIAL_NUMBER: Fraction

  val FRACTIONS: List[Fraction]

  def isValidStartingFraction(fraction: Fraction): Boolean

  /**
   * Indicates whether the specified fraction is a valid state fraction for the
   * FracTran program. The default criterion is that the fraction is an integer
   * (meaning that the fraction's denominator is 1).
   * @param fraction The fraction to test the validity of as a state fraction
   *                 in the program. Two examples: 9 and
   *                 <sup>9</sup>&frasl;<sub>2</sub> in
   *                 [[fractran.PrimeGame PrimeGame]].
   * @return True if the fraction is a valid state fraction, false if it's not.
   *         For example, in PrimeGame, true for 9, false for
   *         <sup>9</sup>&frasl;<sub>2</sub>.
   */
  def isValidStateFraction(fraction: Fraction): Boolean
    = fraction.denominator == 1L

  def isValidEndingFraction(fraction: Fraction): Boolean

  /**
   * Finds the next fraction after the current fraction, found by multiplying
   * each of the fractions in the program until finding one such that the
   * resulting product returns true for
   * [[fractran.FracTranProgram#isValidStateFraction(fractions.Fraction) isValidStateFraction]].
   * @param current The fraction to find the next step for. For example, the
   *                whole number 290 in [[fractran.PrimeGame PrimeGame]].
   * @return The next step fraction. For example, the whole number 770 in
   *         PrimeGame, obtained by multiplying 290 by
   *         <sup>77</sup>&frasl;<sub>29</sub>.
   * @throws NoValidNextStepException If there is no valid next step for the
   *                                  given fraction. For example,
   *                                  [[fractran.FibonacciGame FibonacciGame]]
   *                                  requires whole numbers for valid state
   *                                  fractions, but includes no whole numbers
   *                                  in its list of fractions. So a number
   *                                  like 20 would cause this exception.
   */
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

  def run(start: Fraction = this.INITIAL_NUMBER): Fraction = {
    if (!this.isValidStartingFraction(start)) {
      val excMsg = ("The number " + start.toString
        + " is not a valid starting number for " + this.getClass.getName)
      throw new java.awt.image.ImagingOpException(excMsg)
    }
    var curr = start
    while (!this.isValidEndingFraction(curr)) curr = this.nextStep(curr)
    curr
  }

  def interpret(result: Fraction): Any = {
    if (!this.isValidEndingFraction(result)) {
      val excMsg = ("The number " + result.toString
        + " is not a valid ending number for " + this.getClass.getName)
      throw new IllegalArgumentException(excMsg)
    }
    var counter = -1
    var num = result
    do {
      num = num / 2
      counter += 1
    } while (num.denominator == 1L)
    counter
  }

}
