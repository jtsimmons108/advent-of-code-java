package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day13_2022 extends AbstractDay {

  public Day13_2022() {
    super(2022, 13);
  }

  public String solvePartOne(String input) {
    List<List<String>> grouped = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    int total = 0;
    for (int i = 0; i < grouped.size(); i++) {
      List<String> current = grouped.get(i);
      if (compare(current.get(0), current.get(1)) == -1) {
        total += i + 1;
      }
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    final String DIVIDER_ONE = "[[2]]";
    final String DIVIDER_TWO = "[[6]]";

    List<String> filtered =
        InputUtils.getInputAsList(input).stream()
            .filter(string -> string.length() > 0)
            .collect(Collectors.toList());
    filtered.add(DIVIDER_ONE);
    filtered.add(DIVIDER_TWO);

    Collections.sort(filtered, (first, second) -> compare(first, second));
    int firstIndex = filtered.indexOf(DIVIDER_ONE) + 1;
    int secondIndex = filtered.indexOf(DIVIDER_TWO) + 1;
    return String.valueOf(firstIndex * secondIndex);
  }

  public int compare(String firstObject, String secondObject) {
    List<Object> first = StringUtils.getStringAsType(firstObject, List.class);
    List<Object> second = StringUtils.getStringAsType(secondObject, List.class);

    return compare(first, second, 0);
  }

  public int compare(List<Object> firstList, List<Object> secondList, int index) {
    if (index >= firstList.size()) {
      if (firstList.size() == secondList.size()) {
        return 0;
      }
      return -1;
    }

    if (index >= secondList.size()) {
      return 1;
    }
    Object first = firstList.get(index);
    Object second = secondList.get(index);
    if (first instanceof Integer && second instanceof Integer) {
      int compare = compare((Integer) first, (Integer) second);
      return compare == 0 ? compare(firstList, secondList, index + 1) : compare;
    }

    List<Object> newFirst = first instanceof Integer ? List.of(first) : (List<Object>) first;
    List<Object> newSecond = second instanceof Integer ? List.of(second) : (List<Object>) second;
    int compare = compare(newFirst, newSecond, 0);
    return compare == 0 ? compare(firstList, secondList, index + 1) : compare;
  }

  public int compare(Integer first, Integer second) {
    int diff = first - second;
    return diff == 0 ? diff : diff / Math.abs(diff);
  }
}
