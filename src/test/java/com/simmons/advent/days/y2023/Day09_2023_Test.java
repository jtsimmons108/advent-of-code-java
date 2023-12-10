package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day09_2023_Test extends DayTest {

  private Day09_2023 day;

  public Day09_2023_Test() {
    day = new Day09_2023();
    setDay(day);
  }

  @ParameterizedTest
  @MethodSource("provideInputsForGetNextValue")
  void testGetNextValue(List<Long> sequence, long next) {
    Assertions.assertEquals(next, day.getNextNumber(sequence));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForGetPrevValue")
  void testGetPrevValue(List<Long> sequence, long next) {
    Assertions.assertEquals(next, day.getPrevNumber(sequence));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of("0 3 6 9 12 15\n" + "1 3 6 10 15 21\n" + "10 13 16 21 30 45", "114"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of("0 3 6 9 12 15\n" + "1 3 6 10 15 21\n" + "10 13 16 21 30 45", "2"));
  }

  public static Stream<Arguments> provideInputsForGetNextValue() {
    return Stream.of(
        Arguments.of(new ArrayList<>(List.of(0L, 3L, 6L, 9L, 12L, 15L)), 18L),
        Arguments.of(new ArrayList<>(List.of(1L, 3L, 6L, 10L, 15L, 21L)), 28L),
        Arguments.of(new ArrayList<>(List.of(10L, 13L, 16L, 21L, 30L, 45L)), 68L));
  }

  public static Stream<Arguments> provideInputsForGetPrevValue() {
    return Stream.of(
        Arguments.of(new ArrayList<>(List.of(0L, 3L, 6L, 9L, 12L, 15L)), -3L),
        Arguments.of(new ArrayList<>(List.of(1L, 3L, 6L, 10L, 15L, 21L)), 0L),
        Arguments.of(new ArrayList<>(List.of(10L, 13L, 16L, 21L, 30L, 45L)), 5L));
  }
}
