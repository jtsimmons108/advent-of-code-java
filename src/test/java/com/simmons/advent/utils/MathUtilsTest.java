package com.simmons.advent.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MathUtilsTest {

  @ParameterizedTest
  @CsvSource({"1,2,1", "2,4,2", "6,24,6"})
  void testGCD(long first, long second, long gcd) {
    Assertions.assertEquals(gcd, MathUtils.gcd(first, second));
  }

  @ParameterizedTest
  @CsvSource({"1,2,2", "2,3,6", "4,6,12", "6,12,12"})
  void testLCM(long first, long second, long lcm) {
    Assertions.assertEquals(lcm, MathUtils.lcm(first, second));
  }
}
