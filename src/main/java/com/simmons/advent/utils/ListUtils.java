package com.simmons.advent.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

  public static <T> List<T> getRepeatedList(List<T> original, int repetitions) {
    List<T> expanded = new ArrayList<>(original.size() * repetitions);
    for (int i = 0; i < repetitions; i++) {
      expanded.addAll(original);
    }
    return expanded;
  }
}
