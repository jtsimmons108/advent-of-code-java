package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.Location;
import com.simmons.advent.models.RegexMatch;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.PatternUtils;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day22_2022 extends AbstractDay {

  public static char OPEN = '.';
  public static char WALL = '#';
  public static char INVALID = ' ';

  public static final Map<Direction, Integer> DIR_SCORES =
      Map.of(Direction.RIGHT, 0, Direction.DOWN, 1, Direction.LEFT, 2, Direction.UP, 3);
  public static final Map<Direction, Character> DIR_CHARS =
      Map.of(Direction.RIGHT, '>', Direction.DOWN, 'v', Direction.LEFT, '<', Direction.UP, '^');

  public Day22_2022() {
    super(2022, 22);
  }

  public String solvePartOne(String input) {
    List<String> groups = InputUtils.getInputAsList(input, "\n\n");
    char[][] grid = InputUtils.getInputAsCharGrid(groups.get(0));
    List<RegexMatch> moves =
        PatternUtils.getAllMatchesFromInput(groups.get(1), Pattern.compile("\\d+[LR]?"));

    int startCol = 0;
    while (grid[0][startCol] == INVALID) {
      startCol++;
    }
    Location current = new Location(0, startCol);
    Direction direction = Direction.RIGHT;
    for (RegexMatch move : moves) {
      String val = move.getValue();
      int length;
      char turn = 'N';
      if (val.endsWith("L") || val.endsWith("R")) {
        length = Integer.parseInt(val.substring(0, val.length() - 1));
        turn = val.charAt(val.length() - 1);
      } else {
        length = Integer.parseInt(val);
      }

      int steps = 0;
      Location next = getNextLocation(current, direction, grid);
      while (steps < length && getValueAtLocation(grid, next) != WALL) {
        grid[current.row][current.col] = DIR_CHARS.get(direction);
        current = next;
        next = getNextLocation(current, direction, grid);
        steps++;
      }

      if (turn != 'N') {
        direction = turn == 'L' ? direction.turnLeft() : direction.turnRight();
      } else {
        System.out.println("Not turning");
      }
    }
    System.out.println("Final location " + current);
    System.out.println("Final direction " + direction);
    return String.valueOf(
        1000 * (current.row + 1) + 4 * (current.col + 1) + DIR_SCORES.get(direction));
  }

  public Location getNextLocation(Location location, Direction direction, char[][] grid) {
    final int ROWS = grid.length;
    final int COLS = grid[0].length;
    int nextRow = location.row;
    int nextCol = location.col;

    do {
      nextRow = (nextRow + direction.dr + ROWS) % ROWS;
      nextCol = (nextCol + direction.dc + COLS) % COLS;
    } while (grid[nextRow][nextCol] == INVALID);
    return new Location(nextRow, nextCol);
  }

  public char getValueAtLocation(char[][] grid, Location location) {
    return grid[location.row][location.col];
  }

  public String solvePartTwo(String input) {
    return null;
  }
}
