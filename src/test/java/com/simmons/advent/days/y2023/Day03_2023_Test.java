package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day03_2023_Test extends DayTest {

  private Day03_2023 day;

  public Day03_2023_Test() {
    day = new Day03_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "467..114..\n"
                + "...*......\n"
                + "..35..633.\n"
                + "......#...\n"
                + "617*......\n"
                + ".....+.58.\n"
                + "..592.....\n"
                + "......755.\n"
                + "...$.*....\n"
                + ".664.598..",
            "4361"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "467..114..\n"
                + "...*......\n"
                + "..35..633.\n"
                + "......#...\n"
                + "617*......\n"
                + ".....+.58.\n"
                + "..592.....\n"
                + "......755.\n"
                + "...$.*....\n"
                + ".664.598..",
            "467835"));
  }
}
