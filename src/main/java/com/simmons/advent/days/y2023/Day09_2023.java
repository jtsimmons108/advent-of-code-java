package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Day09_2023 extends AbstractDay {

  public Day09_2023() {
    super(2023, 9);
  }

  public String solvePartOne(String input) {
    long total = 0;
    List<String> lines = InputUtils.getInputAsList(input);
    for (String line : lines) {
      List<Long> ints = StringUtils.extractIntsFromString(line, true);
      total += getNextNumber(ints);
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    long total = 0;
    List<String> lines = InputUtils.getInputAsList(input);
    for (String line : lines) {
      List<Long> ints = StringUtils.extractIntsFromString(line, true);
      total += getPrevNumber(ints);
    }
    return String.valueOf(total);
  }

  public long getNextNumber(List<Long> sequence) {
    List<List<Long>> differences = getDifferences(sequence);

    for (int i = differences.size() - 2; i >= 0; i--) {
      List<Long> prev = differences.get(i + 1);
      List<Long> current = differences.get(i);
      current.add(current.get(current.size() - 1) + prev.get(prev.size() - 1));
    }

    List<Long> first = differences.get(0);
    return first.get(first.size() - 1);
  }

  public long getPrevNumber(List<Long> sequence) {
    List<List<Long>> differences = getDifferences(sequence);

    for (int i = differences.size() - 2; i >= 0; i--) {
      List<Long> prev = differences.get(i + 1);
      List<Long> current = differences.get(i);
      current.add(0, current.get(0) - prev.get(0));
    }

    return differences.get(0).get(0);
  }

  private List<List<Long>> getDifferences(List<Long> sequence) {
    List<List<Long>> differences = new ArrayList<>(List.of(new ArrayList<>(sequence)));
    List<Long> current = sequence;
    while (!current.stream().allMatch(n -> n == 0)) {
      List<Long> next = new ArrayList<>();
      for (int i = 0; i < current.size() - 1; i++) {
        next.add(current.get(i + 1) - current.get(i));
      }
      differences.add(next);
      current = next;
    }
    return differences;
  }
}
