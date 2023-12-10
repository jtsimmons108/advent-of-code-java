package com.simmons.advent.grid;

import com.simmons.advent.error.NaughtyException;

public class GridUtils {

  public static void printGrid(char[][] grid) {
    for (char[] line : grid) {
      for (char c : line) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static boolean isValidLocation(Location location, char[][] grid) {
    return 0 <= location.row
        && location.row < grid.length
        && 0 <= location.col
        && location.col < grid[0].length;
  }

  public static char getValueFromLocation(Location location, char[][] grid) {
    return grid[location.row][location.col];
  }

  public static char[][] copyGrid(char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    char[][] copy = new char[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        copy[r][c] = grid[r][c];
      }
    }
    return copy;
  }

  public static Location getStartLocation(char[][] grid, char startChar) {
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == startChar) {
          return Location.of(r, c);
        }
      }
    }
    throw new NaughtyException("Uanble to find start location");
  }
}
