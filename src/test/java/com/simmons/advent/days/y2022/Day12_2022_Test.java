package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day12_2022_Test extends DayTest {

  private Day12_2022 day;

  public Day12_2022_Test() {
    day = new Day12_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of("Sabqponm\n" + "abcryxxl\n" + "accszExk\n" + "acctuvwj\n" + "abdefghi", "31"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of("Sabqponm\n" + "abcryxxl\n" + "accszExk\n" + "acctuvwj\n" + "abdefghi", "29"));
  }
}
