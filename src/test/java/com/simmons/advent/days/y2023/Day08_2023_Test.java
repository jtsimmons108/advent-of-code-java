package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day08_2023_Test extends DayTest {

  private Day08_2023 day;

  public Day08_2023_Test() {
    day = new Day08_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "LLR\n" + "\n" + "AAA = (BBB, BBB)\n" + "BBB = (AAA, ZZZ)\n" + "ZZZ = (ZZZ, ZZZ)",
            "6"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "LR\n"
                + "\n"
                + "11A = (11B, XXX)\n"
                + "11B = (XXX, 11Z)\n"
                + "11Z = (11B, XXX)\n"
                + "22A = (22B, XXX)\n"
                + "22B = (22C, 22C)\n"
                + "22C = (22Z, 22Z)\n"
                + "22Z = (22B, 22B)\n"
                + "XXX = (XXX, XXX)",
            "6"));
  }
}
