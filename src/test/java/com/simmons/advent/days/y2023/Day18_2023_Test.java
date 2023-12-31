package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day18_2023_Test extends DayTest {

  private Day18_2023 day;

  public Day18_2023_Test() {
    day = new Day18_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "R 6 (#70c710)\n"
                + "D 5 (#0dc571)\n"
                + "L 2 (#5713f0)\n"
                + "D 2 (#d2c081)\n"
                + "R 2 (#59c680)\n"
                + "D 2 (#411b91)\n"
                + "L 5 (#8ceee2)\n"
                + "U 2 (#caa173)\n"
                + "L 1 (#1b58a2)\n"
                + "U 2 (#caa171)\n"
                + "R 2 (#7807d2)\n"
                + "U 3 (#a77fa3)\n"
                + "L 2 (#015232)\n"
                + "U 2 (#7a21e3)",
            "62"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "R 6 (#70c710)\n"
                + "D 5 (#0dc571)\n"
                + "L 2 (#5713f0)\n"
                + "D 2 (#d2c081)\n"
                + "R 2 (#59c680)\n"
                + "D 2 (#411b91)\n"
                + "L 5 (#8ceee2)\n"
                + "U 2 (#caa173)\n"
                + "L 1 (#1b58a2)\n"
                + "U 2 (#caa171)\n"
                + "R 2 (#7807d2)\n"
                + "U 3 (#a77fa3)\n"
                + "L 2 (#015232)\n"
                + "U 2 (#7a21e3)",
            "952408144115"));
  }
}
