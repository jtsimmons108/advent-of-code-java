package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day22_2022_Test extends DayTest {

  private Day22_2022 day;

  public Day22_2022_Test() {
    day = new Day22_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "        ...#\n"
                + "        .#..\n"
                + "        #...\n"
                + "        ....\n"
                + "...#.......#\n"
                + "........#...\n"
                + "..#....#....\n"
                + "..........#.\n"
                + "        ...#....\n"
                + "        .....#..\n"
                + "        .#......\n"
                + "        ......#.\n"
                + "\n"
                + "10R5L5R10L4R5L5",
            "6032"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "        ...#\n"
                + "        .#..\n"
                + "        #...\n"
                + "        ....\n"
                + "...#.......#\n"
                + "........#...\n"
                + "..#....#....\n"
                + "..........#.\n"
                + "        ...#....\n"
                + "        .....#..\n"
                + "        .#......\n"
                + "        ......#.\n"
                + "\n"
                + "10R5L5R10L4R5L5",
            "5031"));
  }
}
