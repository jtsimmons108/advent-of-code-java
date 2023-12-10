package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day20_2022_Test extends DayTest {

  private Day20_2022 day;

  public Day20_2022_Test() {
    day = new Day20_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(Arguments.of("1\n" + "2\n" + "-3\n" + "3\n" + "-2\n" + "0\n" + "4", "3"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of("1\n" + "2\n" + "-3\n" + "3\n" + "-2\n" + "0\n" + "4", "1623178306"));
  }
}
