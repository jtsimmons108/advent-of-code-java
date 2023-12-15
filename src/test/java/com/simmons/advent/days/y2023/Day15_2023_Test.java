package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day15_2023_Test extends DayTest {

  private Day15_2023 day;

  public Day15_2023_Test() {
    day = new Day15_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(Arguments.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7", "1320"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7", "145"));
  }
}
