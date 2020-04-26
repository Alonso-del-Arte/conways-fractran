package calculators

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class NumberTheoreticFunctionsCalculatorTest {

  @Test def testGCDSamePositiveNumber(): Unit = {
    val expected = 10
    val actual = NumberTheoreticFunctionsCalculator.gcd(expected, expected)
    assertEquals(expected, actual)
  }

  @Test def testGCDSameNegativeNumber(): Unit = {
    val expected = 12
    val actual = NumberTheoreticFunctionsCalculator.gcd(-expected, -expected)
    assertEquals(expected, actual)
  }

  @Test def testGCDConsecutivePrimeNumbers(): Unit = {
    lazy val prime: LazyList[Int] = 2 #:: LazyList.from(3).filter(i => prime.takeWhile {
      j => j * j <= i
    }.forall {
      k => i % k != 0
    })
    for (n <- 1 to 100) {
      assertEquals(1, NumberTheoreticFunctionsCalculator.gcd(prime(n), prime(n + 1)))
    }
  }

  @Test def testGCDConsecutiveEvenFibonacciNumbers(): Unit = {
    lazy val fibo: LazyList[Int] = 0 #:: 1 #:: fibo.zip(fibo.tail).map(n => n._1 + n._2)
    for (i <- 0 to 42 by 3) {
      assertEquals(2, NumberTheoreticFunctionsCalculator.gcd(fibo(i), fibo(i + 3)))
    }
  }

}
