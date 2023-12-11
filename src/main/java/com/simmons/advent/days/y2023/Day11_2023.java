package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.models.Range;
import com.simmons.advent.utils.InputUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11_2023 extends AbstractDay {

  public static final char ROCK = '#';

  private final int PART_ONE_AGE = 2;
  private final int PART_TWO_AGE;

  public Day11_2023() {
    super(2023, 11);
    PART_TWO_AGE = 1000000;
  }

  public Day11_2023(int galaxyAge) {
    super(2023, 11);
    PART_TWO_AGE = galaxyAge;
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    List<Location> rocks = GridUtils.getAllLocations(grid, ROCK);
    Set<Integer> emptyRows = getEmptyRows(grid);
    Set<Integer> emptyCols = getEmptyColumns(grid);

    long distance = getTotalDistances(rocks, PART_ONE_AGE, emptyRows, emptyCols);

    return String.valueOf(distance);
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    List<Location> rocks = GridUtils.getAllLocations(grid, ROCK);
    Set<Integer> emptyRows = getEmptyRows(grid);
    Set<Integer> emptyCols = getEmptyColumns(grid);

    long distance = getTotalDistances(rocks, PART_TWO_AGE, emptyRows, emptyCols);

    return String.valueOf(distance);
  }

  private long getTotalDistances(
      List<Location> rocks, long galaxyAge, Set<Integer> emptyRows, Set<Integer> emptyCols) {
    long total = 0;
    for (int i = 0; i < rocks.size(); i++) {
      for (int j = i + 1; j < rocks.size(); j++) {
        total += getDistanceFrom(rocks.get(i), rocks.get(j), galaxyAge, emptyRows, emptyCols);
      }
    }
    return total;
  }

  private long getDistanceFrom(
      Location from, Location to, long galaxyAge, Set<Integer> emptyRows, Set<Integer> emptyCols) {
    long distance = 0;

    Range rowRange = Range.of(Math.min(from.row, to.row), Math.max(from.row, to.row));
    distance += rowRange.length();
    distance += emptyRows.stream().filter(rowRange::contains).count() * (galaxyAge - 1);

    Range colRange = Range.of(Math.min(from.col, to.col), Math.max(from.col, to.col));
    distance += colRange.length();
    distance += emptyCols.stream().filter(colRange::contains).count() * (galaxyAge - 1);

    return distance;
  }

  private Set<Integer> getEmptyColumns(char[][] grid) {
    Set<Integer> emptyCols = new HashSet<>();
    for (int c = 0; c < cols(grid); c++) {
      boolean empty = true;
      for (int r = 0; r < rows(grid); r++) {
        if (grid[r][c] == ROCK) {
          empty = false;
        }
      }
      if (empty) {
        emptyCols.add(c);
      }
    }
    return emptyCols;
  }

  private Set<Integer> getEmptyRows(char[][] grid) {
    Set<Integer> emptyRows = new HashSet<>();
    for (int r = 0; r < rows(grid); r++) {
      boolean empty = true;
      for (int c = 0; c < cols(grid); c++) {
        if (grid[r][c] == ROCK) {
          empty = false;
        }
      }
      if (empty) {
        emptyRows.add(r);
      }
    }
    return emptyRows;
  }
}
