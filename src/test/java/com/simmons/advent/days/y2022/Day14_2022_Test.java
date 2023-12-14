package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day14_2022_Test extends DayTest {

  private Day14_2022 day;

  public Day14_2022_Test() {
    day = new Day14_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of("498,4 -> 498,6 -> 496,6\n" + "503,4 -> 502,4 -> 502,9 -> 494,9", "24"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of("498,4 -> 498,6 -> 496,6\n" + "503,4 -> 502,4 -> 502,9 -> 494,9", "93"));
  }
}
