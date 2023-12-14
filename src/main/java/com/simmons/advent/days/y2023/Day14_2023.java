package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.Direction.*;
import static com.simmons.advent.grid.GridUtils.*;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.ArrayList;
import java.util.List;

public class Day14_2023 extends AbstractDay {

  public static final char ROUND_ROCK = 'O';
  public static final char ROCK = '#';

  public static final int TARGET = 1000000000;

  public Day14_2023() {
    super(2023, 14);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    rollAllRocks(grid, UP);
    return String.valueOf(getSupportScore(grid));
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    List<Direction> order = List.of(UP, LEFT, DOWN, RIGHT);
    List<String> startingGrids = new ArrayList<>();
    String current = GridUtils.getAsString(grid);
    while (!startingGrids.contains(current)) {
      startingGrids.add(current);
      for (Direction direction : order) {
        rollAllRocks(grid, direction);
      }
      current = GridUtils.getAsString(grid);
    }
    int cycleStart = startingGrids.indexOf(current);
    int cycleLength = startingGrids.size() - cycleStart;
    int finalEnd = (TARGET - cycleStart) % cycleLength + cycleStart;

    String finalString = startingGrids.get(finalEnd);
    char[][] finalGrid = InputUtils.getInputAsCharGrid(finalString);

    return String.valueOf(getSupportScore(finalGrid));
  }

  private int getSupportScore(char[][] grid) {
    return GridUtils.getAllLocations(grid, ROUND_ROCK).stream()
        .mapToInt(r -> grid.length - r.row)
        .sum();
  }

  private void rollAllRocks(char[][] grid, Direction direction) {
    if (direction == UP) {
      for (int row = 0; row < rows(grid); row++) {
        for (int col = 0; col < cols(grid); col++) {
          if (grid[row][col] == ROUND_ROCK) {
            roll(grid, Location.of(row, col), UP);
          }
        }
      }
    } else if (direction == DOWN) {
      for (int row = rows(grid) - 1; row >= 0; row--) {
        for (int col = 0; col < cols(grid); col++) {
          if (grid[row][col] == ROUND_ROCK) {
            roll(grid, Location.of(row, col), DOWN);
          }
        }
      }
    } else if (direction == LEFT) {
      for (int col = 0; col < cols(grid); col++) {
        for (int row = 0; row < rows(grid); row++) {
          if (grid[row][col] == ROUND_ROCK) {
            roll(grid, Location.of(row, col), LEFT);
          }
        }
      }
    } else {
      for (int col = cols(grid) - 1; col >= 0; col--) {
        for (int row = 0; row < rows(grid); row++) {
          if (grid[row][col] == ROUND_ROCK) {
            roll(grid, Location.of(row, col), RIGHT);
          }
        }
      }
    }
  }

  private void roll(char[][] grid, Location loc, Direction direction) {
    Location current = loc;
    Location next = current.next(direction);
    while (GridUtils.isValidLocation(next, grid)
        && GridUtils.getValueFromLocation(next, grid) != ROUND_ROCK
        && GridUtils.getValueFromLocation(next, grid) != ROCK) {
      current = next;
      next = current.next(direction);
    }

    if (!current.equals(loc)) {
      GridUtils.setValueAtLocation(current, grid, 'O');
      GridUtils.setValueAtLocation(loc, grid, '.');
    }
  }
}
