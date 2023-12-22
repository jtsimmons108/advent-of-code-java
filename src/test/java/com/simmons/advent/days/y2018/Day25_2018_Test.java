package com.simmons.advent.days.y2018;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day25_2018_Test extends DayTest {

  private Day25_2018 day;

  public Day25_2018_Test() {
    day = new Day25_2018();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            " 0,0,0,0\n"
                + " 3,0,0,0\n"
                + " 0,3,0,0\n"
                + " 0,0,3,0\n"
                + " 0,0,0,3\n"
                + " 0,0,0,6\n"
                + " 9,0,0,0\n"
                + "12,0,0,0",
            "2"),
        Arguments.of(
            "-1,2,2,0\n"
                + "0,0,2,-2\n"
                + "0,0,0,-2\n"
                + "-1,2,0,0\n"
                + "-2,-2,-2,2\n"
                + "3,0,2,-1\n"
                + "-1,3,2,2\n"
                + "-1,0,-1,0\n"
                + "0,2,1,-2\n"
                + "3,0,0,0",
            "4"),
        Arguments.of(
            "1,-1,0,1\n"
                + "2,0,-1,0\n"
                + "3,2,-1,0\n"
                + "0,0,3,1\n"
                + "0,0,-1,-1\n"
                + "2,3,-2,0\n"
                + "-2,2,0,0\n"
                + "2,-2,0,-1\n"
                + "1,-1,0,-1\n"
                + "3,2,0,2",
            "3"),
        Arguments.of(
            "1,-1,-1,-2\n"
                + "-2,-2,0,1\n"
                + "0,2,1,3\n"
                + "-2,3,-2,1\n"
                + "0,2,3,-2\n"
                + "-1,-1,1,-2\n"
                + "0,-2,-1,0\n"
                + "-2,2,3,-1\n"
                + "1,2,2,0\n"
                + "-1,-2,0,-2",
            "8"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("", "50"));
  }
}
