package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

public class Day03_2022_Test extends DayTest {

  private Day03_2022 day;

  public Day03_2022_Test() {
    day = new Day03_2022();
    setDay(day);
  }

  @ParameterizedTest
  @CsvSource({
    "vJrwpWtwJgWrhcsFMMfFFhFp,16",
    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,38",
    "PmmdzqPrVvPwwTWBwg,42",
    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,22",
    "ttgJtRGJQctTZtZT,20",
    "CrZsJsPPZsGzwwsLwLmpwMDw,19"
  })
  void testGetRucksackScore(String rucksack, String score) {
    Assertions.assertEquals(
        score, String.valueOf(((Day03_2022) day).getScoreFromSingleRucksack(rucksack)));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp\n"
                + "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n"
                + "PmmdzqPrVvPwwTWBwg\n"
                + "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n"
                + "ttgJtRGJQctTZtZT\n"
                + "CrZsJsPPZsGzwwsLwLmpwMDw",
            "157"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp\n"
                + "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n"
                + "PmmdzqPrVvPwwTWBwg\n"
                + "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n"
                + "ttgJtRGJQctTZtZT\n"
                + "CrZsJsPPZsGzwwsLwLmpwMDw",
            "70"));
  }
}
