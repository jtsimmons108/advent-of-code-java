package com.simmons.advent.days.y2023;

import com.simmons.advent.days.base.DayTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Day10_2023_Test extends DayTest {

  private Day10_2023 day;

  public Day10_2023_Test() {
    day = new Day10_2023();
    setDay(day);
  }

  public static Stream<Arguments> provideInputsForPart1() {
    return Stream.of(
        Arguments.of(".....\n" + ".S-7.\n" + ".|.|.\n" + ".L-J.\n" + ".....", "4"),
        Arguments.of("-L|F7\n" + "7S-7|\n" + "L|7||\n" + "-L-J|\n" + "L|-JF", "4"),
        Arguments.of("..F7.\n" + ".FJ|.\n" + "SJ.L7\n" + "|F--J\n" + "LJ...", "8"),
        Arguments.of("7-F7-\n" + ".FJ|7\n" + "SJLL7\n" + "|F--J\n" + "LJ.LJ", "8"));
  }

  public static Stream<Arguments> provideInputsForPart2() {
    return Stream.of(
        Arguments.of(
            "...........\n"
                + ".S-------7.\n"
                + ".|F-----7|.\n"
                + ".||.....||.\n"
                + ".||.....||.\n"
                + ".|L-7.F-J|.\n"
                + ".|..|.|..|.\n"
                + ".L--J.L--J.\n"
                + "...........",
            "4"),
        Arguments.of(
            "..........\n"
                + ".S------7.\n"
                + ".|F----7|.\n"
                + ".||OOOO||.\n"
                + ".||OOOO||.\n"
                + ".|L-7F-J|.\n"
                + ".|II||II|.\n"
                + ".L--JL--J.\n"
                + "..........",
            "4"),
        Arguments.of(
            ".F----7F7F7F7F-7....\n"
                + ".|F--7||||||||FJ....\n"
                + ".||.FJ||||||||L7....\n"
                + "FJL7L7LJLJ||LJ.L-7..\n"
                + "L--J.L7...LJS7F-7L7.\n"
                + "....F-J..F7FJ|L7L7L7\n"
                + "....L7.F7||L7|.L7L7|\n"
                + ".....|FJLJ|FJ|F7|.LJ\n"
                + "....FJL-7.||.||||...\n"
                + "....L---J.LJ.LJLJ...",
            "8"),
        Arguments.of(
            "FF7FSF7F7F7F7F7F---7\n"
                + "L|LJ||||||||||||F--J\n"
                + "FL-7LJLJ||||||LJL-77\n"
                + "F--JF--7||LJLJ7F7FJ-\n"
                + "L---JF-JLJ.||-FJLJJ7\n"
                + "|F|F-JF---7F7-L7L|7|\n"
                + "|FFJF7L7F-JF7|JL---7\n"
                + "7-L-JL7||F7|L7F-7F7|\n"
                + "L.L7LFJ|||||FJL7||LJ\n"
                + "L7JLJL-JLJLJL--JLJ.L",
            "10"));
  }
}
