package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day04_2023_Test extends DayTest {

  private Day04_2023 day;

  public Day04_2023_Test() {
    day = new Day04_2023();
    setDay(day);
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTestWinningNumbers")
  void testWinningNumbers(String line, int winningNumbers) {
    Assertions.assertEquals(winningNumbers, day.getWinningNumbers(line));
  }

  public static Stream<Arguments> provideInputsForTestWinningNumbers() {
    return Stream.of(
        Arguments.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", 4),
        Arguments.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", 2),
        Arguments.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", 2),
        Arguments.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", 1),
        Arguments.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", 0),
        Arguments.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11", 0));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n"
                + "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n"
                + "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n"
                + "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n"
                + "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n"
                + "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
            "13"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n"
                + "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n"
                + "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n"
                + "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n"
                + "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n"
                + "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
            "30"));
  }
}
