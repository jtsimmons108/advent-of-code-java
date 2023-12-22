package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day20_2023_Test extends DayTest {

  private Day20_2023 day;

  public Day20_2023_Test() {
    day = new Day20_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "broadcaster -> a\n"
                + "%a -> inv, con\n"
                + "&inv -> b\n"
                + "%b -> con\n"
                + "&con -> output",
            "11687500"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "broadcaster -> a\n"
                + "%a -> inv, con\n"
                + "&inv -> b\n"
                + "%b -> con\n"
                + "&con -> output",
            ""));
  }
}
