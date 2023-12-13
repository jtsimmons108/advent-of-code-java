package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day12_2023_Test extends DayTest {

  private Day12_2023 day;

  public Day12_2023_Test() {
    day = new Day12_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "???.### 1,1,3\n"
                + ".??..??...?##. 1,1,3\n"
                + "?#?#?#?#?#?#?#? 1,3,1,6\n"
                + "????.#...#... 4,1,1\n"
                + "????.######..#####. 1,6,5\n"
                + "?###???????? 3,2,1",
            "21"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "???.### 1,1,3\n"
                + ".??..??...?##. 1,1,3\n"
                + "?#?#?#?#?#?#?#? 1,3,1,6\n"
                + "????.#...#... 4,1,1\n"
                + "????.######..#####. 1,6,5\n"
                + "?###???????? 3,2,1",
            "525152"));
  }
}
