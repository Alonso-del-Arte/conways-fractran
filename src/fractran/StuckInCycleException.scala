package fractran

import fractions.Fraction

class StuckInCycleException(msg: String, val cycleFractions: List[Fraction])
  extends RuntimeException(msg)