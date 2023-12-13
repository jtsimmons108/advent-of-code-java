package com.simmons.advent.grid;

import com.simmons.advent.error.NaughtyException;
import java.util.ArrayList;
import java.util.List;

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

  public static void setValueAtLocation(Location location, char[][] grid, char value) {
    grid[location.row][location.col] = value;
  }

  public static char[][] copyGrid(char[][] grid) {
    char[][] copy = new char[rows(grid)][cols(grid)];
    for (int r = 0; r < rows(grid); r++) {
      for (int c = 0; c < cols(grid); c++) {
        copy[r][c] = grid[r][c];
      }
    }
    return copy;
  }

  public static Location getStartLocation(char[][] grid, char startChar) {
    for (int r = 0; r < rows(grid); r++) {
      for (int c = 0; c < cols(grid); c++) {
        if (grid[r][c] == startChar) {
          return Location.of(r, c);
        }
      }
    }
    throw new NaughtyException("Uanble to find start location");
  }

  public static List<Location> getAllLocations(char[][] grid, char target) {
    List<Location> locations = new ArrayList<>();
    for (int r = 0; r < rows(grid); r++) {
      for (int c = 0; c < cols(grid); c++) {
        if (grid[r][c] == target) {
          locations.add(Location.of(r, c));
        }
      }
    }
    return locations;
  }

  public static final int rows(char[][] grid) {
    return grid.length;
  }

  public static final int cols(char[][] grid) {
    return grid[0].length;
  }
}
