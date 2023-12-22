package com.simmons.advent.days.y2022;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day16_2022_Test extends DayTest {

  private Day16_2022 day;

  public Day16_2022_Test() {
    day = new Day16_2022();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(
            "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n"
                + "Valve BB has flow rate=13; tunnels lead to valves CC, AA\n"
                + "Valve CC has flow rate=2; tunnels lead to valves DD, BB\n"
                + "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n"
                + "Valve EE has flow rate=3; tunnels lead to valves FF, DD\n"
                + "Valve FF has flow rate=0; tunnels lead to valves EE, GG\n"
                + "Valve GG has flow rate=0; tunnels lead to valves FF, HH\n"
                + "Valve HH has flow rate=22; tunnel leads to valve GG\n"
                + "Valve II has flow rate=0; tunnels lead to valves AA, JJ\n"
                + "Valve JJ has flow rate=21; tunnel leads to valve II",
            "1651"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(Arguments.of("", ""));
  }
}
