package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day12_2022 extends AbstractDay {

  public static final char START = 'S';
  public static final char END = 'E';
  public static final char LOWEST = 'a';

  public Day12_2022() {
    super(2022, 12);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    Location START_LOC = null;
    Location END_LOC = null;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == START) {
          START_LOC = new Location(row, col, START);
        } else if (grid[row][col] == END) {
          END_LOC = new Location(row, col, END);
        }
      }
    }

    Map<Location, Integer> steps = new HashMap<>();
    steps.put(START_LOC, 0);
    Deque<Move> moves = new ArrayDeque<>();
    moves.add(new Move(START_LOC, 0));

    while (!steps.containsKey(END_LOC) && moves.size() > 0) {
      Move current = moves.poll();
      if (current != null) {
        for (Delta delta : Delta.values()) {
          int row = current.location.row + delta.dr;
          int col = current.location.col + delta.dc;
          if (isValidLocation(row, col, grid)) {
            Location possible = new Location(row, col, grid[row][col]);
            int nextSteps = current.steps + 1;
            if (current.location.canMoveTo(possible)
                && (!steps.containsKey(possible) || steps.get(possible) > nextSteps)) {
              steps.put(possible, nextSteps);
              moves.add(new Move(possible, nextSteps));
            }
          }
        }
      }
    }
    return String.valueOf(steps.get(END_LOC));
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    List<Location> startLocations = new ArrayList<>();
    Location END_LOC = null;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == START) {
          grid[row][col] = LOWEST;
          startLocations.add(new Location(row, col, LOWEST));
        } else if (grid[row][col] == LOWEST) {
          startLocations.add(new Location(row, col, LOWEST));
        } else if (grid[row][col] == END) {
          END_LOC = new Location(row, col, END);
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (Location start : startLocations) {
      Map<Location, Integer> steps = new HashMap<>();
      steps.put(start, 0);
      Deque<Move> moves = new ArrayDeque<>();
      moves.add(new Move(start, 0));

      while (!steps.containsKey(END_LOC) && moves.size() > 0) {
        Move current = moves.poll();

        for (Delta delta : Delta.values()) {
          int row = current.location.row + delta.dr;
          int col = current.location.col + delta.dc;
          if (isValidLocation(row, col, grid)) {
            Location possible = new Location(row, col, grid[row][col]);
            int nextSteps = current.steps + 1;
            if (current.location.canMoveTo(possible)
                && (!steps.containsKey(possible) || steps.get(possible) > nextSteps)) {
              steps.put(possible, nextSteps);
              moves.add(new Move(possible, nextSteps));
            }
          }
        }
      }
      if (steps.containsKey(END_LOC)) {
        min = Math.min(min, steps.get(END_LOC));
      }
    }
    return String.valueOf(min);
  }

  private boolean isValidLocation(int row, int col, char[][] grid) {
    return 0 <= row && row < grid.length && 0 <= col && col < grid[0].length;
  }

  @Data
  @AllArgsConstructor
  class Location {
    final int row;
    final int col;
    final char val;

    public boolean canMoveTo(Location location) {
      if (location.val == END) {
        return this.val == 'z';
      }
      if (this.val == START) {
        return location.val == 'a';
      }
      return location.val - this.val <= 1;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d] = %c", row, col, val);
    }
  }

  @Data
  class Move {
    final Location location;
    final int steps;
  }

  enum Delta {
    LEFT(0, -1),
    RIGHT(0, 1),
    UP(-1, 0),
    DOWN(1, 0);

    final int dr;
    final int dc;

    Delta(int dr, int dc) {
      this.dr = dr;
      this.dc = dc;
    }
  }
}
