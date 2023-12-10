package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day25_2022_Test extends DayTest {

  private Day25_2022 day;

  public Day25_2022_Test() {
    day = new Day25_2022();
    setDay(day);
  }

  @ParameterizedTest
  @MethodSource("provideInputsForGetDecimalFromSnafu")
  void testConvertSnafuToDecimal(String snafu, long decimal) {
    Assertions.assertEquals(decimal, day.getDecimalFromSnafu(snafu));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForGetSnafuFromDecimal")
  void testConvertDecimalToSnafu(long decimal, String snafu) {
    Assertions.assertEquals(snafu, day.getSnafuFromDecimal(decimal));
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "1=-0-2\n"
                + "12111\n"
                + "2=0=\n"
                + "21\n"
                + "2=01\n"
                + "111\n"
                + "20012\n"
                + "112\n"
                + "1=-1=\n"
                + "1-12\n"
                + "12\n"
                + "1=\n"
                + "122",
            "2=-1=0"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("", "49"));
  }

  public static Stream<Arguments> provideInputsForGetDecimalFromSnafu() {
    return Stream.of(
        Arguments.of("1=-0-2", 1747),
        Arguments.of("12111", 906),
        Arguments.of("2=0=", 198),
        Arguments.of("21", 11),
        Arguments.of("2=01", 201),
        Arguments.of("111", 31),
        Arguments.of("20012", 1257),
        Arguments.of("112", 32),
        Arguments.of("1=-1=", 353),
        Arguments.of("1-12", 107),
        Arguments.of("12", 7),
        Arguments.of("1=", 3),
        Arguments.of("122", 37));
  }

  public static Stream<Arguments> provideInputsForGetSnafuFromDecimal() {
    return Stream.of(
        Arguments.of(1747, "1=-0-2"),
        Arguments.of(906, "12111"),
        Arguments.of(198, "2=0="),
        Arguments.of(11, "21"),
        Arguments.of(201, "2=01"),
        Arguments.of(31, "111"),
        Arguments.of(1257, "20012"),
        Arguments.of(32, "112"),
        Arguments.of(353, "1=-1="),
        Arguments.of(107, "1-12"),
        Arguments.of(7, "12"),
        Arguments.of(3, "1="),
        Arguments.of(37, "122"));
  }
}
