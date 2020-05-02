package fractran

import fractions.Fraction

trait Streamable extends FracTranProgram {

  val stream: LazyList[Fraction] = LazyList.iterate(this.INITIAL_NUMBER)(this.nextStep)

}
