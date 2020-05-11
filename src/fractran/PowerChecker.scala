package fractran

import fractions.Fraction

class PowerChecker(val base: Fraction) extends FracTranProgram with Streamable {

  override val INITIAL_NUMBER: Fraction = base

  override val FRACTIONS: List[Fraction] = List(base.reciprocal, base)

  private val unity = new Fraction(1, 1)

  override def isValidStartingFraction(fraction: Fraction): Boolean =
    fraction.numerator != 0L

  override def isValidStateFraction(fraction: Fraction): Boolean = true

  override def isValidEndingFraction(fraction: Fraction): Boolean = (fraction == base
    || fraction == unity || fraction == base.reciprocal
    || fraction.denominator == base.reciprocal.denominator)

  override def nextStep(current: Fraction): Fraction = {
    if (base.isInteger == current.isInteger) {
      current * base.reciprocal
    } else current * base
  }

  override def run(start: Fraction): Fraction = {
    val initCond = start.isInteger
    val selFrac = if (base.isInteger && start.isInteger) base.reciprocal else base
    var curr = start
    do curr = curr * selFrac while (initCond == curr.isInteger && curr != unity)
    curr
  }

  override def interpret(result: Fraction): Boolean = (result == base
    || result == unity
    || result == base.reciprocal)

}
