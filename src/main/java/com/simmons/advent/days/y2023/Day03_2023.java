package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.RegexMatch;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.PatternUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day03_2023 extends AbstractDay {

  public static final Pattern SYMBOL = Pattern.compile("[^0-9\\.]");

  public Day03_2023() {
    super(2023, 3);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<String> paddedGrid = getPaddedGrid(lines);
    Map<PartNumber, List<Sybmol>> links = linkPartsToSymbol(paddedGrid);
    int total = 0;
    for (Map.Entry<PartNumber, List<Sybmol>> link : links.entrySet()) {
      if (link.getValue().size() > 0) {
        total += link.getKey().getNumber();
      }
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<String> paddedGrid = getPaddedGrid(lines);
    Map<PartNumber, List<Sybmol>> links = linkPartsToSymbol(paddedGrid);
    Map<Sybmol, List<Integer>> matches = new HashMap<>();
    for (Map.Entry<PartNumber, List<Sybmol>> entry : links.entrySet()) {
      for (Sybmol symbol : entry.getValue()) {
        matches.computeIfAbsent(symbol, s -> new ArrayList<>()).add(entry.getKey().getNumber());
      }
    }
    long total = 0;
    for (Map.Entry<Sybmol, List<Integer>> entry : matches.entrySet()) {
      if (entry.getKey().getSymbol() == '*' && entry.getValue().size() == 2) {
        total += (long) entry.getValue().get(0) * entry.getValue().get(1);
      }
    }
    return String.valueOf(total);
  }

  public Map<PartNumber, List<Sybmol>> linkPartsToSymbol(List<String> paddedGrid) {
    Map<PartNumber, List<Sybmol>> links = new HashMap<>();
    for (int row = 1; row < paddedGrid.size() - 1; row++) {
      List<RegexMatch> matches =
          PatternUtils.getAllMatchesFromInput(paddedGrid.get(row), PatternUtils.INT_PATTERN);
      for (RegexMatch match : matches) {
        int col = match.getStart();

        int left = col - 1;
        int right = match.getEnd() + 1;

        String localGrid =
            paddedGrid.get(row - 1).substring(left, right)
                + paddedGrid.get(row).substring(left, right)
                + paddedGrid.get(row + 1).substring(left, right);
        links.put(
            new PartNumber(Integer.parseInt(match.getValue()), row, col),
            getSymbolsFromLocalGrid(localGrid, row, col));
      }
    }
    return links;
  }

  private List<Sybmol> getSymbolsFromLocalGrid(String localGrid, int row, int col) {
    List<Sybmol> symbols = new ArrayList<>();
    int lineLength = localGrid.length() / 3;
    Matcher symbolMatcher = SYMBOL.matcher(localGrid);
    while (symbolMatcher.find()) {
      int rowLoc = row + symbolMatcher.start() / lineLength - 1;
      int colLoc = col + symbolMatcher.start() % lineLength - 1;
      symbols.add(new Sybmol(symbolMatcher.group().charAt(0), rowLoc, colLoc));
    }
    return symbols;
  }

  private List<String> getPaddedGrid(List<String> original) {
    List<String> newGrid =
        original.stream().map(line -> "." + line + ".").collect(Collectors.toList());

    String border = StringUtils.getRepeatedString(".", newGrid.get(0).length());
    newGrid.add(0, border);
    newGrid.add(border);
    return newGrid;
  }

  @Data
  @AllArgsConstructor
  class PartNumber {
    final int number;
    final int row, col;
  }

  @Data
  @AllArgsConstructor
  class Sybmol {
    final char symbol;
    final int row, col;
  }
}
