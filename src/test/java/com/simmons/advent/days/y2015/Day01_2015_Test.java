package com.simmons.advent.days.y2015;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day01_2015_Test extends DayTest {

  private Day01_2015 day;

  public Day01_2015_Test() {
    day = new Day01_2015();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of("(())", "0"),
        Arguments.of("()()", "0"),
        Arguments.of("(((", "3"),
        Arguments.of("(()(()(", "3"),
        Arguments.of("))(((((", "3"),
        Arguments.of("())", "-1"),
        Arguments.of("))(", "-1"),
        Arguments.of(")))", "-3"),
        Arguments.of(")())())", "-3"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of(")", "1"), Arguments.of("()())", "5"));
  }
}
