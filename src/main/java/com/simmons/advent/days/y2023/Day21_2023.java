package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.Direction.*;
import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day21_2023 extends AbstractDay {

  public static final int GOAL = 64;
  public static final char START = 'S';
  public static final char ROCK = '#';
  public final int goal;
  public static final List<Direction> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);

  public Day21_2023(int goal) {
    super(2023, 21);
    this.goal = goal;
  }

  public Day21_2023() {
    this(GOAL);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    Location start = GridUtils.getStartLocation(grid, START);

    return String.valueOf(getSpacesReached(goal, start, grid));
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);

    Location start = GridUtils.getStartLocation(grid, START);
    final long BASE = 202300L;
    final long ROWS = rows(grid);
    final long HALF_GRID = ROWS / 2;
    final long FULL_GRID = ROWS;
    final long TARGET_PART2 = BASE * FULL_GRID + HALF_GRID;

    long y1 = getSpacesReached(HALF_GRID, start, grid);
    long y2 = getSpacesReached(HALF_GRID + FULL_GRID, start, grid);
    long y3 = getSpacesReached(HALF_GRID + 2 * FULL_GRID, start, grid);

    long c = y1;
    long b = (4 * y2 - 3 * y1 - y3) / 2;
    long a = (y3 - 2 * y2 + y1) / 2;

    long x = (TARGET_PART2 - HALF_GRID) / FULL_GRID;
    long answer = a * x * x + b * x + c;
    return String.valueOf(answer);
  }

  private long getSpacesReached(long limit, Location start, char[][] grid) {
    final int ROWS = rows(grid);
    final int COLS = cols(grid);
    Set<Location> seen = new HashSet<>();
    Set<Location> answers = new HashSet<>();
    Deque<Step> steps = new ArrayDeque<>();
    steps.add(new Step(start, 0));
    while (!steps.isEmpty()) {
      Step current = steps.poll();
      if (!seen.contains(current.location)) {
        seen.add(current.location);
        if (current.stepCount % 2 == limit % 2) {
          answers.add(current.location);
        }
        for (Direction dir : DIRECTIONS) {
          Location next = current.location.next(dir);
          if (!(GridUtils.getValueFromLocation(
                      new Location(getNumInRange(next.row, ROWS), getNumInRange(next.col, COLS)),
                      grid)
                  == ROCK)
              && current.stepCount < limit) {
            steps.add(new Step(next, current.stepCount + 1));
          }
        }
      }
    }

    return answers.size();
  }

  @Data
  @AllArgsConstructor
  class Step {
    Location location;
    int stepCount;
  }

  private int getNumInRange(int num, int range) {
    return ((num % range) + range) % range;
  }
}
