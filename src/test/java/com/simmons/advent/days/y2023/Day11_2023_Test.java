package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day11_2023_Test extends DayTest {

  private Day11_2023 day;

  public Day11_2023_Test() {
    day = new Day11_2023(10);
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "...#......\n"
                + ".......#..\n"
                + "#.........\n"
                + "..........\n"
                + "......#...\n"
                + ".#........\n"
                + ".........#\n"
                + "..........\n"
                + ".......#..\n"
                + "#...#.....",
            "374"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "...#......\n"
                + ".......#..\n"
                + "#.........\n"
                + "..........\n"
                + "......#...\n"
                + ".#........\n"
                + ".........#\n"
                + "..........\n"
                + ".......#..\n"
                + "#...#.....",
            "1030"));
  }
}
