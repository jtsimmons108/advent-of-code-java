package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day14_2023_Test extends DayTest {

  private Day14_2023 day;

  public Day14_2023_Test() {
    day = new Day14_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "O....#....\n"
                + "O.OO#....#\n"
                + ".....##...\n"
                + "OO.#O....O\n"
                + ".O.....O#.\n"
                + "O.#..O.#.#\n"
                + "..O..#O..O\n"
                + ".......O..\n"
                + "#....###..\n"
                + "#OO..#....",
            "136"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "O....#....\n"
                + "O.OO#....#\n"
                + ".....##...\n"
                + "OO.#O....O\n"
                + ".O.....O#.\n"
                + "O.#..O.#.#\n"
                + "..O..#O..O\n"
                + ".......O..\n"
                + "#....###..\n"
                + "#OO..#....",
            "64"));
  }
}
