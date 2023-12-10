package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day15_2022_Test extends DayTest {

  private Day15_2022 day;

  public Day15_2022_Test() {
    day = new Day15_2022(20);
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n"
                + "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n"
                + "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n"
                + "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n"
                + "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n"
                + "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n"
                + "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n"
                + "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n"
                + "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n"
                + "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n"
                + "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n"
                + "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n"
                + "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n"
                + "Sensor at x=20, y=1: closest beacon is at x=15, y=3",
            "26"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n"
                    + "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n"
                    + "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n"
                    + "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n"
                    + "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n"
                    + "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n"
                    + "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n"
                    + "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n"
                    + "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n"
                    + "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n"
                    + "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n"
                    + "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n"
                    + "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n"
                    + "Sensor at x=20, y=1: closest beacon is at x=15, y=3",
            "56000011"));
  }
}
