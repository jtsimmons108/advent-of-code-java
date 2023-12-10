package com.simmons.advent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtils {

  public static List<String> getInputAsList(String input) {
    return getInputAsList(input, "\n");
  }

  public static List<String> getInputAsList(String input, String separator) {
    return Arrays.asList(input.split(separator));
  }

  public static List<Integer> getInputsAsIntList(String input) {
    return getInputAsList(input).stream().map(Integer::valueOf).collect(Collectors.toList());
  }

  public static List<String> getInputAsList(String input, String separator, int limit) {
    return Arrays.asList(input.split(separator, limit));
  }

  public static char[][] getInputAsCharGrid(String input) {
    List<String> lines = getInputAsList(input);
    int maxLength = lines.stream().mapToInt(line -> line.length()).max().getAsInt();
    final int ROWS = lines.size();
    final int COLS = maxLength;
    char[][] grid = new char[ROWS][COLS];
    for (int row = 0; row < ROWS; row++) {
      String line = lines.get(row);
      for (int col = 0; col < COLS; col++) {
        char val = col < line.length() ? line.charAt(col) : ' ';
        grid[row][col] = val;
      }
    }
    return grid;
  }

  public static List<List<String>> getInputAs2DList(
      String input, String firstSep, String secondSep) {
    return Arrays.stream(input.split(firstSep))
        .map(string -> Arrays.stream(string.split(secondSep)).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }
}
