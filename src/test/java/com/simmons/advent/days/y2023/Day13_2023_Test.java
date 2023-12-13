package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day13_2023_Test extends DayTest {

  private Day13_2023 day;

  public Day13_2023_Test() {
    day = new Day13_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "#.##..##.\n"
                + "..#.##.#.\n"
                + "##......#\n"
                + "##......#\n"
                + "..#.##.#.\n"
                + "..##..##.\n"
                + "#.#.##.#.\n"
                + "\n"
                + "#...##..#\n"
                + "#....#..#\n"
                + "..##..###\n"
                + "#####.##.\n"
                + "#####.##.\n"
                + "..##..###\n"
                + "#....#..#",
            "405"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("", ""));
  }
}
