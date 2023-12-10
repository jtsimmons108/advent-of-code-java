package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day01_2022 extends AbstractDay {

  public Day01_2022() {
    super(2022, 1);
  }

  public String solvePartOne(String input) {
    List<Long> weights = getSortedWeightListFromInput(input);
    return String.valueOf(weights.get(0));
  }

  public String solvePartTwo(String input) {
    List<Long> weights = getSortedWeightListFromInput(input);
    return String.valueOf(weights.get(0) + weights.get(1) + weights.get(2));
  }

  private List<Long> getSortedWeightListFromInput(String input) {
    List<List<String>> separated = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    List<Long> weights =
        separated.stream()
            .map(group -> group.stream().mapToLong(Long::parseLong).sum())
            .collect(Collectors.toList());
    Collections.sort(weights);
    Collections.reverse(weights);
    return weights;
  }
}
