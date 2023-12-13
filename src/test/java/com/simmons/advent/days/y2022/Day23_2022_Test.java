package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day23_2022_Test extends DayTest {

  private Day23_2022 day;

  public Day23_2022_Test() {
    day = new Day23_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "....#..\n"
                + "..###.#\n"
                + "#...#.#\n"
                + ".#...##\n"
                + "#.###..\n"
                + "##.#.##\n"
                + ".#..#..",
            "110"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "....#..\n"
                + "..###.#\n"
                + "#...#.#\n"
                + ".#...##\n"
                + "#.###..\n"
                + "##.#.##\n"
                + ".#..#..",
            "20"));
  }
}
