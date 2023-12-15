package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15_2023 extends AbstractDay {

  public Day15_2023() {
    super(2023, 15);
  }

  public String solvePartOne(String input) {
    long total = 0;
    for (String entry : input.split(",")) {
      total += hash(entry);
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    Map<Integer, Map<String, Integer>> values = new HashMap<>();
    Map<Integer, List<String>> orders = new HashMap<>();

    for (String entry : input.split(",")) {
      boolean containsDash = entry.endsWith("-");
      String label =
          containsDash
              ? entry.substring(0, entry.length() - 1)
              : entry.substring(0, entry.indexOf('='));

      int hashValue = hash(label);
      Map<String, Integer> currentValues = values.computeIfAbsent(hashValue, k -> new HashMap<>());
      List<String> currentOrder = orders.computeIfAbsent(hashValue, k -> new ArrayList<>());

      if (containsDash) {
        currentValues.remove(label);
        currentOrder.remove(label);
      } else {
        int value = Integer.parseInt(entry.substring(label.length() + 1));
        currentValues.put(label, value);
        if (!currentOrder.contains(label)) {
          currentOrder.add(label);
        }
      }
    }

    long total = 0;
    for (Map.Entry<Integer, Map<String, Integer>> entry : values.entrySet()) {
      int box = entry.getKey();
      Map<String, Integer> boxValues = entry.getValue();
      List<String> boxOrders = orders.get(box);
      for (String val : boxValues.keySet()) {
        total += (box + 1) * (boxOrders.indexOf(val) + 1) * boxValues.get(val);
      }
    }

    return String.valueOf(total);
  }

  private int hash(String input) {
    int total = 0;
    for (int i = 0; i < input.length(); i++) {
      total += input.charAt(i);
      total *= 17;
      total %= 256;
    }
    return total;
  }
}
