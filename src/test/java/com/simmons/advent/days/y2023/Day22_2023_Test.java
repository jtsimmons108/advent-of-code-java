package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day22_2023_Test extends DayTest {

  private Day22_2023 day;

  public Day22_2023_Test() {
    day = new Day22_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "1,0,1~1,2,1\n"
                + "0,0,2~2,0,2\n"
                + "0,2,3~2,2,3\n"
                + "0,0,4~0,2,4\n"
                + "2,0,5~2,2,5\n"
                + "0,1,6~2,1,6\n"
                + "1,1,8~1,1,9",
            "5"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "1,0,1~1,2,1\n"
                + "0,0,2~2,0,2\n"
                + "0,2,3~2,2,3\n"
                + "0,0,4~0,2,4\n"
                + "2,0,5~2,2,5\n"
                + "0,1,6~2,1,6\n"
                + "1,1,8~1,1,9",
            "7"));
  }
}
