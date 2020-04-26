package calculators

object NumberTheoreticFunctionsCalculator {

  def gcd(a: Long, b: Long): Long = b match {
    case 0 => a
    case _ => Math.abs(gcd(b, a % b))
  }

}
