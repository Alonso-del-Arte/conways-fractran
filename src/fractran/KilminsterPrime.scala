package fractran

import fractions.Fraction

object KilminsterPrime extends FracTranProgram with Streamable {

  override val INITIAL_NUMBER: Fraction = new Fraction(10, 1)

  override val FRACTIONS: List[Fraction] = List(Fraction(7, 3), Fraction(99, 98),
    Fraction(13, 49), Fraction(39, 35), Fraction(36, 91), Fraction(10, 143),
    Fraction(49, 13), Fraction(7, 11), Fraction(1, 2), Fraction(91, 1))

  private val powerOfTenChecker = new PowerChecker(10)

  override def isValidStartingFraction(fraction: Fraction): Boolean =
    fraction.denominator == 1L

  override def isValidEndingFraction(fraction: Fraction): Boolean =
    powerOfTenChecker.interpret(powerOfTenChecker.run(fraction))

}
