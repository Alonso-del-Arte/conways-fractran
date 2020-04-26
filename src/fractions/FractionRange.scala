package fractions
// TODO: Flesh out
class FractionRange(val start: Fraction, val end: Fraction, val step: Fraction) {

  def this(start: Fraction, end: Fraction) {
    this(start, end, Fraction.inferStep(start, end))
  }

  // STUB
  def apply(n: Int): Fraction = new Fraction(1)

}
