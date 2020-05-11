package fractran

import fractions.Fraction

/**
 * Indicates that a [[fractran.FracTranProgram FracTranProgram]] has no halting
 * numbers and therefore it is possible to create a lazy stream that won't
 * cause [[fractran.NoValidNextStepException NoValidNextStepException]].
 */
trait Streamable  {

  this: FracTranProgram =>
  /**
   * Provides a lazy stream of fractions through the iterated application of
   * the [[fractran.FracTranProgram#nextStep(fractions.Fraction) nextStep]]
   * function.
   */
  val stream: LazyList[Fraction] =
    LazyList.iterate(this.INITIAL_NUMBER)(this.nextStep)

}
