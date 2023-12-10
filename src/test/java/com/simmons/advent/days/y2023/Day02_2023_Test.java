package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day02_2023_Test extends DayTest {

  private Day02_2023 day;

  public Day02_2023_Test() {
    day = new Day02_2023();
    setDay(day);
  }

  @ParameterizedTest
  @MethodSource("provideInputsForIsValidGameMethod")
  void testIsValidGame(String line, boolean expected) {
    Assertions.assertEquals(expected, day.isValidGame(line));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForGetPowerMethod")
  void testGetPower(String line, int expected) {
    Assertions.assertEquals(expected, day.getPower(line));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n"
                + "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n"
                + "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n"
                + "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n"
                + "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
            "8"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n"
                + "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n"
                + "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n"
                + "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n"
                + "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
            "2286"));
  }

  public static Stream<Arguments> provideInputsForIsValidGameMethod() {
    return Stream.of(
        Arguments.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", true),
        Arguments.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", true),
        Arguments.of(
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", false),
        Arguments.of(
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", false),
        Arguments.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", true));
  }

  public static Stream<Arguments> provideInputsForGetPowerMethod() {
    return Stream.of(
        Arguments.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", 48),
        Arguments.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", 12),
        Arguments.of(
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", 1560),
        Arguments.of(
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", 630),
        Arguments.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", 36));
  }
}
