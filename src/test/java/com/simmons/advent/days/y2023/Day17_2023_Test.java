package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day17_2023_Test extends DayTest {

  private Day17_2023 day;

  public Day17_2023_Test() {
    day = new Day17_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "2413432311323\n"
                + "3215453535623\n"
                + "3255245654254\n"
                + "3446585845452\n"
                + "4546657867536\n"
                + "1438598798454\n"
                + "4457876987766\n"
                + "3637877979653\n"
                + "4654967986887\n"
                + "4564679986453\n"
                + "1224686865563\n"
                + "2546548887735\n"
                + "4322674655533",
            "102"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "2413432311323\n"
                + "3215453535623\n"
                + "3255245654254\n"
                + "3446585845452\n"
                + "4546657867536\n"
                + "1438598798454\n"
                + "4457876987766\n"
                + "3637877979653\n"
                + "4654967986887\n"
                + "4564679986453\n"
                + "1224686865563\n"
                + "2546548887735\n"
                + "4322674655533",
            "94"));
  }
}
