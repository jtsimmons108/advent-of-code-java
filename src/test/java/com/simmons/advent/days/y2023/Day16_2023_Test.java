package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day16_2023_Test extends DayTest {

  private Day16_2023 day;

  public Day16_2023_Test() {
    day = new Day16_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            ".|...\\....\n"
                + "|.-.\\.....\n"
                + ".....|-...\n"
                + "........|.\n"
                + "..........\n"
                + ".........\\\n"
                + "..../.\\\\..\n"
                + ".-.-/..|..\n"
                + ".|....-|.\\\n"
                + "..//.|....",
            "46"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            ".|...\\....\n"
                + "|.-.\\.....\n"
                + ".....|-...\n"
                + "........|.\n"
                + "..........\n"
                + ".........\\\n"
                + "..../.\\\\..\n"
                + ".-.-/..|..\n"
                + ".|....-|.\\\n"
                + "..//.|....",
            "51"));
  }
}
