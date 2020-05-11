package fractran

import fractions.Fraction

/**
 * Conway's PrimeGame. Gives the prime numbers as exponents for powers of 2.
 */
object PrimeGame extends FracTranProgram with Streamable {

  override val INITIAL_NUMBER: Fraction = Fraction(2, 1)

  override val FRACTIONS: List[Fraction] = List(Fraction(17, 91),
    Fraction(78, 85), Fraction(19, 51), Fraction(23, 38), Fraction(29, 33),
    Fraction(77, 29), Fraction(1, 17), Fraction(11, 13), Fraction(13, 11),
    Fraction(15, 2), Fraction(1, 7), new Fraction(55))

  override def isValidStartingFraction(fraction: Fraction): Boolean
    = fraction.denominator == 1L

  override def isValidEndingFraction(fraction: Fraction): Boolean
    = (fraction.denominator == 1L) && (fraction.numerator ==
      java.lang.Long.highestOneBit(fraction.numerator))

}
