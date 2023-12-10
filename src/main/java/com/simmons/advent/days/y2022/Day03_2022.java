package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.StringUtils;
import java.util.Arrays;

public class Day03_2022 extends AbstractDay {

  public Day03_2022() {
    super(2022, 3);
  }

  public String solvePartOne(String input) {
    int total =
        Arrays.stream(input.split("\n"))
            .mapToInt(string -> getScoreFromSingleRucksack(string))
            .sum();
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    String[] split = input.split("\n");
    int total = 0;
    for (int i = 0; i < split.length; i += 3) {
      total += getScoreFromRucksackGroup(split[i], split[i + 1], split[i + 2]);
    }
    return String.valueOf(total);
  }

  public int getScoreFromSingleRucksack(String rucksack) {
    int half = rucksack.length() / 2;
    char common =
        StringUtils.getCommonCharacters(rucksack.substring(0, half), rucksack.substring(half))
            .iterator()
            .next();
    return getScoreFromChar(common);
  }

  public int getScoreFromRucksackGroup(String... rucksacks) {
    char common = StringUtils.getCommonCharacters(rucksacks).iterator().next();
    return getScoreFromChar(common);
  }

  private int getScoreFromChar(char c) {
    return Character.isUpperCase(c) ? c - 'A' + 1 + 26 : c - 'a' + 1;
  }
}
