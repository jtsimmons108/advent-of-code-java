package com.simmons.advent.days.y2015;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;

public class Day01_2015 extends AbstractDay {

  public Day01_2015() {
    super(2015, 1);
  }

  public String solvePartOne(String input) {
    int floor = 0;
    for (char c : input.toCharArray()) {
      switch (c) {
        case '(':
          floor++;
          break;
        case ')':
          floor--;
          break;
      }
    }
    return String.valueOf(floor);
  }

  public String solvePartTwo(String input) {
    int floor = 0;
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      switch (c) {
        case '(':
          floor++;
          break;
        case ')':
          floor--;
          break;
      }
      if (floor == -1) {
        return String.valueOf(i + 1);
      }
    }
    throw new NaughtyException("This should not happen");
  }
}
