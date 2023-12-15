package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day17_2022_Test extends DayTest {

  private Day17_2022 day;

  public Day17_2022_Test() {
    day = new Day17_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(Arguments.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>", "3068"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>", "1514285714288"));
  }
}
