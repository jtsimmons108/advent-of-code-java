package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10_2023 extends AbstractDay {

  public static final char START = 'S';

  public Day10_2023() {
    super(2023, 10);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    List<Location> path = getLoopPathFromGrid(grid);
    return String.valueOf(path.size() / 2);
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    final int ROWS = grid.length;
    final int COLS = grid[0].length;

    List<Location> path = getLoopPathFromGrid(grid);
    Polygon polyPath = new Polygon();
    for (Location loc : path) {
      polyPath.addPoint(loc.col, loc.row);
    }

    Set<Location> pathSet = new HashSet<>(path);

    int contained = 0;
    for (int r = 0; r < ROWS; r++) {
      for (int c = 0; c < COLS; c++) {
        Location loc = Location.of(r, c);
        if (!pathSet.contains(loc) && polyPath.contains(c, r)) {
          contained++;
        }
      }
    }
    return String.valueOf(contained);
  }

  public List<Location> getLoopPathFromGrid(char[][] grid) {
    Location startLocation = GridUtils.getStartLocation(grid, START);
    Direction currentDir = getStartDirection(startLocation, grid);

    List<Location> path = new ArrayList<>();
    Location currentLocation = startLocation;

    while (path.isEmpty() || !path.get(path.size() - 1).equals(startLocation)) {
      Location next = currentLocation.next(currentDir);
      path.add(next);
      currentDir = getNextDirection(currentDir, getValueFromLocation(next, grid));
      currentLocation = next;
    }
    return path;
  }

  private Direction getStartDirection(Location startLocation, char[][] grid) {
    for (Direction dir : Direction.values()) {
      if (canMove(startLocation, dir, grid)) {
        return dir;
      }
    }
    throw new NaughtyException("Unable to find starting direction");
  }

  private Direction getNextDirection(Direction direction, char val) {
    if (direction == Direction.RIGHT) {
      if (val == '7') {
        return Direction.DOWN;
      }
      if (val == 'J') {
        return Direction.UP;
      }
    } else if (direction == Direction.LEFT) {
      if (val == 'L') {
        return Direction.UP;
      }
      if (val == 'F') {
        return Direction.DOWN;
      }
    } else if (direction == Direction.DOWN) {
      if (val == 'L') {
        return Direction.RIGHT;
      }
      if (val == 'J') {
        return Direction.LEFT;
      }
    } else { // UP
      if (val == 'F') {
        return Direction.RIGHT;
      }
      if (val == '7') {
        return Direction.LEFT;
      }
    }
    return direction;
  }

  private char getValueFromLocation(Location location, char[][] grid) {
    return grid[location.row][location.col];
  }

  private boolean canMove(Location location, Direction direction, char[][] grid) {
    char nextVal = getValueFromLocation(location.next(direction), grid);
    if (direction == Direction.RIGHT) {
      return nextVal == 'J' || nextVal == '-' || nextVal == '7';
    } else if (direction == Direction.LEFT) {
      return nextVal == 'F' || nextVal == '-' || nextVal == 'L';
    } else if (direction == Direction.DOWN) {
      return nextVal == 'L' || nextVal == '|' || nextVal == 'J';
    } else { // UP
      return nextVal == 'F' || nextVal == '7' || nextVal == '|';
    }
  }
}
