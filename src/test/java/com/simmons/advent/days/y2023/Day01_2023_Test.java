package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

public class Day01_2023_Test extends DayTest {

  private Day01_2023 day;

  public Day01_2023_Test() {
    day = new Day01_2023();
    setDay(day);
  }

  @ParameterizedTest
  @CsvSource({"1abc2,12", "pqr3stu8vwx,38", "a1b2c3d4e5f,15", "treb7uchet,77", "9459994599,99"})
  void testGetCalibrationValue(String line, int expected) {
    Assertions.assertEquals(expected, day.getCalibrationValue(line, Day01_2023.DIGIT_PATTERN));
  }

  @ParameterizedTest
  @CsvSource({
    "two1nine,29",
    "eightwothree,83",
    "abcone2threexyz,13",
    "xtwone3four,24",
    "4nineeightseven2,42",
    "zoneight234,14",
    "7pqrstsixteen,76",
    "eighthree,83",
    "sevenine,79"
  })
  void testGetCalibrationValueWithSpelling(String line, int expected) {
    Assertions.assertEquals(
        expected, day.getCalibrationValue(line, Day01_2023.SPELLED_DIGIT_PATTERN));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of("1abc2\n" + "pqr3stu8vwx\n" + "a1b2c3d4e5f\n" + "treb7uchet", "142"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "two1nine\n"
                + "eightwothree\n"
                + "abcone2threexyz\n"
                + "xtwone3four\n"
                + "4nineeightseven2\n"
                + "zoneight234\n"
                + "7pqrstsixteen",
            "281"));
  }
}
