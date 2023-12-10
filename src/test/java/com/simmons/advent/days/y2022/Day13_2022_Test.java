package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class Day13_2022_Test extends DayTest {

  private Day13_2022 day;

  public Day13_2022_Test() {
    day = new Day13_2022();
    setDay(day);
  }

  @ParameterizedTest
  @CsvSource({"1,1,0", "1,2,-1", "2,1,1", "0,7,-1", "7,0,1"})
  void testCompareIntegers(int first, int second, int expected) {
    Day13_2022 day = (Day13_2022) this.day;
    Assertions.assertEquals(expected, day.compare(first, second));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForCompare")
  void testCompareInputs(String first, String second, int expected) {
    Day13_2022 day = (Day13_2022) this.day;
    Assertions.assertEquals(expected, day.compare(first, second));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "[1,1,3,1,1]\n"
                + "[1,1,5,1,1]\n"
                + "\n"
                + "[[1],[2,3,4]]\n"
                + "[[1],4]\n"
                + "\n"
                + "[9]\n"
                + "[[8,7,6]]\n"
                + "\n"
                + "[[4,4],4,4]\n"
                + "[[4,4],4,4,4]\n"
                + "\n"
                + "[7,7,7,7]\n"
                + "[7,7,7]\n"
                + "\n"
                + "[]\n"
                + "[3]\n"
                + "\n"
                + "[[[]]]\n"
                + "[[]]\n"
                + "\n"
                + "[1,[2,[3,[4,[5,6,7]]]],8,9]\n"
                + "[1,[2,[3,[4,[5,6,0]]]],8,9]",
            "13"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "[1,1,3,1,1]\n"
                + "[1,1,5,1,1]\n"
                + "\n"
                + "[[1],[2,3,4]]\n"
                + "[[1],4]\n"
                + "\n"
                + "[9]\n"
                + "[[8,7,6]]\n"
                + "\n"
                + "[[4,4],4,4]\n"
                + "[[4,4],4,4,4]\n"
                + "\n"
                + "[7,7,7,7]\n"
                + "[7,7,7]\n"
                + "\n"
                + "[]\n"
                + "[3]\n"
                + "\n"
                + "[[[]]]\n"
                + "[[]]\n"
                + "\n"
                + "[1,[2,[3,[4,[5,6,7]]]],8,9]\n"
                + "[1,[2,[3,[4,[5,6,0]]]],8,9]",
            "140"));
  }

  public static Stream<Arguments> provideInputsForCompare() {
    return Stream.of(
        Arguments.of("[1,1,3,1,1]", "[1,1,5,1,1]", -1),
        Arguments.of("[[1],[2,3,4]]", "[[1],4]", -1),
        Arguments.of("[9]", "[[8,7,6]]", 1),
        Arguments.of("[[4,4],4,4]", "[[4,4],4,4,4]", -1),
        Arguments.of("[7,7,7,7]", "[7,7,7]", 1),
        Arguments.of("[]", "[3]", -1),
        Arguments.of("[[[]]]", "[[]]", 1),
        Arguments.of("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]", 1));
  }
}
