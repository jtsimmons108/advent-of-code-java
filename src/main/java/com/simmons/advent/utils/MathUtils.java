package com.simmons.advent.utils;

public class MathUtils {

  private MathUtils() {}

  public static long gcd(long a, long b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }

  public static long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }
}
