package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02_2023 extends AbstractDay {

  public static final Map<String, Integer> MAXES = Map.of("red", 12, "green", 13, "blue", 14);
  public static final Map<String, Integer> INDICES = Map.of("red", 0, "green", 1, "blue", 2);
  public static final Pattern CUBE_PATTERN = Pattern.compile("(\\d+) (red|green|blue)");

  public Day02_2023() {
    super(2023, 2);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    int total = 0;
    for (int game = 1; game <= lines.size(); game++) {
      if (isValidGame(lines.get(game - 1))) {
        total += game;
      }
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    int total = lines.stream().mapToInt(this::getPower).sum();
    return String.valueOf(total);
  }

  public boolean isValidGame(String line) {
    Matcher matcher = CUBE_PATTERN.matcher(line);
    while (matcher.find()) {
      int num = Integer.parseInt(matcher.group(1));
      String color = matcher.group(2);
      if (num > MAXES.get(color)) {
        return false;
      }
    }
    return true;
  }

  public int getPower(String line) {
    int[] mins = {0, 0, 0};
    Matcher matcher = CUBE_PATTERN.matcher(line);
    while (matcher.find()) {
      int num = Integer.parseInt(matcher.group(1));
      int index = INDICES.get(matcher.group(2));
      mins[index] = Math.max(num, mins[index]);
    }
    return mins[0] * mins[1] * mins[2];
  }
}
