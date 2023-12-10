package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day06_2023_Test extends DayTest {

  private Day06_2023 day;

  public Day06_2023_Test() {
    day = new Day06_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(Arguments.of("Time:      7  15   30\n" + "Distance:  9  40  200", "288"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("Time:      7  15   30\n" + "Distance:  9  40  200", "71503"));
  }
}
