package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day18_2022_Test extends DayTest {

  private Day18_2022 day;

  public Day18_2022_Test() {
    day = new Day18_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "2,2,2\n" + "1,2,2\n" + "3,2,2\n" + "2,1,2\n" + "2,3,2\n" + "2,2,1\n" + "2,2,3\n"
                + "2,2,4\n" + "2,2,6\n" + "1,2,5\n" + "3,2,5\n" + "2,1,5\n" + "2,3,5",
            "64"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("", ""));
  }
}
