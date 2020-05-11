package fractran

import fractions.Fraction

/**
 * Indicates that a [[fractran.FracTranProgram FracTranProgram]] couldn't find
 * a valid next step for the input fraction. The program tried multiplying the
 * input fraction by each fraction in its list of fractions and couldn't find
 * one that resulted in a product for which
 * [[fractran.FracTranProgram#isValidStateFraction(fractions.Fraction) isValidStateFraction]]
 * returns true.
 * This is a runtime exception. It is necessary because
 * [[fractran.Streamable#stream() stream]] from
 * [[fractran.Streamable Streamable]] could unexpectedly lead to this situation
 * hundreds or thousands of fractions after the initial fraction.
 * @param msg      The exception message. For example, "FibonacciGame couldn't
 *                 find an integer to follow 20."
 * @param cause    The exception to wrap. For example,
 *                 <code>NoSuchElementException</code>.
 * @param fraction The fraction for which the program couldn't find a
 *                 successor. For example, in
 *                 [[fractran.FibonacciGame FibonacciGame]], which requires
 *                 whole numbers for successors, the whole number 20 should
 *                 cause this exception, because none of the FibonacciGame
 *                 fractions multiplied by 20 give a whole number (e.g., 20
 *                 &times; <sup>13</sup>&frasl;<sub>65</sub> =
 *                 <sup>68</sup>&frasl;<sub>13</sub> and 20 &times;
 *                 <sup>133</sup>&frasl;<sub>34</sub> =
 *                 <sup>1330</sup>&frasl;<sub>17</sub>.)
 */
class NoValidNextStepException(msg: String, cause: Throwable, val fraction: Fraction)
  extends RuntimeException(msg, cause)