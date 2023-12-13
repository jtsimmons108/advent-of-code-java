package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.List;

public class Day13_2023 extends AbstractDay {

  public Day13_2023() {
    super(2023, 13);
  }

  public String solvePartOne(String input) {
    List<String> groups = InputUtils.getInputAsList(input, "\n\n");
    long total = 0;
    for (String line : groups) {
      char[][] grid = InputUtils.getInputAsCharGrid(line);
      total += 100 * getHorizontalReflectionLine(grid) + getVerticalReflectionLine(grid);
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input, "\n\n");
    long total = 0;
    for (String line : lines) {
      char[][] grid = InputUtils.getInputAsCharGrid(line);
      total +=
          100 * getReflectedRowAfterFixingHorizontalSmudge(grid)
              + getReflectedColAfterFixingVerticalSmudge(grid);
    }

    return String.valueOf(total);
  }

  private int getHorizontalReflectionLine(char[][] grid) {
    for (int r = 1; r < rows(grid); r++) {
      if (isReflectedOverRow(r, grid)) {
        return r;
      }
    }
    return 0;
  }

  private int getVerticalReflectionLine(char[][] grid) {
    for (int c = 1; c < cols(grid); c++) {
      if (isReflectedOverColumn(c, grid)) {
        return c;
      }
    }
    return 0;
  }

  private boolean isReflectedOverRow(int row, char[][] grid) {
    int ROWS = rows(grid);
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < cols(grid); c++) {
        int mirroredRow = row + (row - r - 1);
        if (r < ROWS && mirroredRow < ROWS && grid[r][c] != grid[mirroredRow][c]) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isReflectedOverColumn(int col, char[][] grid) {
    int COLS = cols(grid);
    for (int c = 0; c < col; c++) {
      for (int r = 0; r < rows(grid); r++) {
        int mirroredCol = col + (col - c - 1);
        if (c < COLS && mirroredCol < COLS && grid[r][c] != grid[r][mirroredCol]) {
          return false;
        }
      }
    }
    return true;
  }

  private int getReflectedRowAfterFixingHorizontalSmudge(char[][] grid) {
    for (int r = 1; r < rows(grid); r++) {
      if (hasSingleHorizontalSmudge(r, grid)) {
        return r;
      }
    }
    return 0;
  }

  private int getReflectedColAfterFixingVerticalSmudge(char[][] grid) {
    for (int c = 1; c < cols(grid); c++) {
      if (hasSingleVerticalSmudge(c, grid)) {
        return c;
      }
    }
    return 0;
  }

  private boolean hasSingleHorizontalSmudge(int row, char[][] grid) {
    int ROWS = rows(grid);
    int count = 0;
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < cols(grid); c++) {
        int mirroredRow = row + (row - r - 1);
        if (r < ROWS && mirroredRow < ROWS && grid[r][c] != grid[mirroredRow][c]) {
          count++;
        }
      }
    }
    return count == 1;
  }

  private boolean hasSingleVerticalSmudge(int col, char[][] grid) {
    int COLS = cols(grid);
    int count = 0;
    for (int c = 0; c < col; c++) {
      for (int r = 0; r < rows(grid); r++) {
        int mirroredCol = col + (col - c - 1);
        if (c < COLS && mirroredCol < COLS && grid[r][c] != grid[r][mirroredCol]) {
          count++;
        }
      }
    }
    return count == 1;
  }
}
