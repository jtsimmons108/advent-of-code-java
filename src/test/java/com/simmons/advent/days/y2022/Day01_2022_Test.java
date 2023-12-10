package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day01_2022_Test extends DayTest {

  private Day01_2022 day;

  public Day01_2022_Test() {
    day = new Day01_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "1000\n" + "2000\n" + "3000\n" + "\n" + "4000\n" + "\n" + "5000\n" + "6000\n" + "\n"
                + "7000\n" + "8000\n" + "9000\n" + "\n" + "10000",
            "24000"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "1000\n" + "2000\n" + "3000\n" + "\n" + "4000\n" + "\n" + "5000\n" + "6000\n" + "\n"
                + "7000\n" + "8000\n" + "9000\n" + "\n" + "10000",
            "45000"));
  }
}
