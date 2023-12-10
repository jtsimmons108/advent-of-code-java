package com.simmons.advent.days.base;

import com.simmons.advent.days.model.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class DayTest {

  private Day day;

  public DayTest() {}

  public void setDay(Day day) {
    this.day = day;
  }

  @ParameterizedTest
  @MethodSource("provideInputsForPart1")
  void testPart1(String input, String output) {
    Assertions.assertEquals(output, day.solvePartOne(input));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForPart2")
  void testPart2(String input, String output) {
    Assertions.assertEquals(output, day.solvePartTwo(input));
  }
}
