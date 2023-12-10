package com.simmons.advent;

import com.simmons.advent.days.model.Day;
import com.simmons.advent.error.NaughtyException;

public class DayRunner {

  public static final int DAYS = 25;

  public static void main(String[] args) {
    runSingleDay(2023, 10, true);
  }

  public static void runSingleDay(int year, int day, boolean timed) {
    String className = String.format("com.simmons.advent.days.y%d.Day%02d_%d", year, day, year);
    try {
      Day dayObj =
          (Day)
              Class.forName(className)
                  .getDeclaredConstructor(new Class[0])
                  .newInstance(new Object[0]);
      dayObj.printSolutionsForDay(timed);
    } catch (Exception e) {
      throw new NaughtyException("Unable to run day", e);
    }
  }

  public static void runAllDaysSingleYear(int year, boolean timed) {
    for (int day = 1; day <= DAYS; day++) {
      runSingleDay(year, day, timed);
    }
  }

  public static void runAllDays(boolean timed) {
    for (int year = 2015; year < 2024; year++) {
      runAllDaysSingleYear(year, timed);
    }
  }
}
