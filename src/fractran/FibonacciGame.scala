package fractran

import fractions.Fraction

object FibonacciGame extends FracTranProgram {

  override val INITIAL_NUMBER: Fraction = Fraction(78, 1)

  // Sloane's OEIS A275483 / A275484
  // 17, 133, 17, 23, 2233, 23, 31,  74, 31, 41, 129, 41, 13, 1,  1
  // 65,  34, 19, 17,   69, 29, 23, 341, 37, 31, 287, 43, 41, 13, 3
  override val FRACTIONS: List[Fraction] = List(Fraction(17, 65),
    Fraction(133, 34), Fraction(17, 19), Fraction(23, 17), Fraction(2233, 69),
    Fraction(23, 29), Fraction(31, 23), Fraction(74, 341), Fraction(31, 37),
    Fraction(41, 31), Fraction(129, 287), Fraction(41, 43), Fraction(13, 41),
    Fraction(1, 13), Fraction(1, 3))

  private val ONE_FIFTH = new Fraction(1, 5)

  private def isPowerOfFive(n: Fraction): Boolean = {
    var fract = n
    while (fract.denominator == 1L) {
      fract = fract * ONE_FIFTH
    }
    fract.numerator == 1L
  }

  override def isValidStartingFraction(fraction: Fraction): Boolean =
    (fraction.numerator % 78 == 0) && fraction.denominator == 1L &&
      isPowerOfFive(fraction / 78)

  override def isValidEndingFraction(fraction: Fraction): Boolean =
    (fraction.denominator == 1L) && (fraction.numerator ==
      java.lang.Long.highestOneBit(fraction.numerator))

}
