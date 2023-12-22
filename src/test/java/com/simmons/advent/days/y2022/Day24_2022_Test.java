package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day24_2022_Test extends DayTest {

  private Day24_2022 day;

  public Day24_2022_Test() {
    day = new Day24_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "#.######\n" + "#>>.<^<#\n" + "#.<..<<#\n" + "#>v.><>#\n" + "#<^v^^>#\n" + "######.#",
            "18"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "#.######\n" + "#>>.<^<#\n" + "#.<..<<#\n" + "#>v.><>#\n" + "#<^v^^>#\n" + "######.#",
            "54"));
  }
}
