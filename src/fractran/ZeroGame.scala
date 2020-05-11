package fractran
import fractions.Fraction

/**
 * Just gives zero. To be used only as the default program for FracTran.
 */
object ZeroGame extends FracTranProgram with Streamable {

  override val INITIAL_NUMBER: Fraction = new Fraction(1, 1)

  override val FRACTIONS: List[Fraction] = List(Fraction(0, 1))

  override def isValidStartingFraction(fraction: Fraction): Boolean = fraction.numerator != 0L

  override def isValidStateFraction(fraction: Fraction): Boolean = true

  override def isValidEndingFraction(fraction: Fraction): Boolean = fraction.numerator == 0L

  override def interpret(result: Fraction): Any = 0

}
