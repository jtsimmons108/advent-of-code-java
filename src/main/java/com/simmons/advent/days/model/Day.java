package com.simmons.advent.days.model;

import org.apache.commons.lang.time.StopWatch;

public interface Day {
  String solvePartOne(String input);

  String solvePartTwo(String input);

  String getInput();

  public default void printSolutionsForDay(boolean timed) {
    StopWatch watch = new StopWatch();
    watch.start();
    String part1 = solvePartOne(getInput());
    watch.split();
    if (part1 != null) {
      System.out.println(this.getClass().getSimpleName());

      System.out.println("******************************");
      System.out.println(String.format("Part 1: %s", part1));
      if (timed) {
        System.out.println(String.format("Part 1 Time: %d ms", watch.getSplitTime()));
      }
      watch.split();
      String part2 = solvePartTwo(getInput());
      watch.stop();
      System.out.println(String.format("Part 2: %s", part2));
      if (timed) {
        System.out.println(String.format("Part 2 Time: %d ms", watch.getSplitTime()));
      }
      System.out.println("******************************");
    }
  }
}
