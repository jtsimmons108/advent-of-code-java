package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.ListUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day12_2023 extends AbstractDay {

  public static final Map<State, Long> CACHE = new HashMap<>();

  public Day12_2023() {
    super(2023, 12);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    long total = 0;

    for (String line : lines) {
      int space = line.indexOf(" ");
      String check = line.substring(0, space);
      String countString = line.substring(space + 1);
      List<Integer> counts =
          Arrays.stream(countString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
      total += getTotalCount(check, counts, 0, 0);
      CACHE.clear();
    }

    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    long total = 0;

    for (String line : lines) {
      int space = line.indexOf(" ");
      String check = line.substring(0, space);
      String countString = line.substring(space + 1);
      List<Integer> counts =
          Arrays.stream(countString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
      total +=
          getTotalCount(
              StringUtils.getRepeatedString(check, 5, "?"),
              ListUtils.getRepeatedList(counts, 5),
              0,
              0);
      CACHE.clear();
    }

    return String.valueOf(total);
  }

  private long getTotalCount(String line, List<Integer> groups, int index, int count) {
    if (index == line.length()) {
      if (groups.size() == 0 && count == 0) {
        return 1;
      }
      if (groups.size() == 1 && groups.get(0) == count) {
        return 1;
      }
      return 0;
    }
    char c = line.charAt(index);
    State state = new State(c, index, count, groups.toString());
    if (CACHE.containsKey(state)) {
      return CACHE.get(state);
    }

    long total = 0;

    if (c == '.' || c == '?') {
      if (count == 0) {
        total += getTotalCount(line, groups, index + 1, 0);
      } else if (!groups.isEmpty() && groups.get(0) == count) {
        total += getTotalCount(line, groups.subList(1, groups.size()), index + 1, 0);
      }
    }

    if (c == '#' || c == '?') {
      total += getTotalCount(line, groups, index + 1, count + 1);
    }
    CACHE.put(state, total);
    return total;
  }

  @Data
  @AllArgsConstructor
  class State {
    final char c;
    final int index;
    final int count;
    String groups;
  }
}
