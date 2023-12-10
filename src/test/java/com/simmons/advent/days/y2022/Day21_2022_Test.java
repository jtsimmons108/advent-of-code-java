package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day21_2022_Test extends DayTest {

  private Day21_2022 day;

  public Day21_2022_Test() {
    day = new Day21_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "root: pppw + sjmn\n"
                + "dbpl: 5\n"
                + "cczh: sllz + lgvd\n"
                + "zczc: 2\n"
                + "ptdq: humn - dvpt\n"
                + "dvpt: 3\n"
                + "lfqf: 4\n"
                + "humn: 5\n"
                + "ljgn: 2\n"
                + "sjmn: drzm * dbpl\n"
                + "sllz: 4\n"
                + "pppw: cczh / lfqf\n"
                + "lgvd: ljgn * ptdq\n"
                + "drzm: hmdt - zczc\n"
                + "hmdt: 32",
            "152"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of("1\n" + "2\n" + "-3\n" + "3\n" + "-2\n" + "0\n" + "4", "1623178306"));
  }
}
