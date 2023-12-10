package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.PatternUtils;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01_2023 extends AbstractDay {

  public static final Map<String, String> SPELLED =
      Map.of(
          "one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6", "seven", "7",
          "eight", "8", "nine", "9");

  public static final Pattern DIGIT_PATTERN = PatternUtils.getOverlappingPattern("[1-9]");
  public static final Pattern SPELLED_DIGIT_PATTERN =
      PatternUtils.getOverlappingPattern("[1-9]|one|two|three|four|five|six|seven|eight|nine");

  public Day01_2023() {
    super(2023, 1);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    int total = lines.stream().mapToInt(line -> getCalibrationValue(line, DIGIT_PATTERN)).sum();
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    int total =
        lines.stream().mapToInt(line -> getCalibrationValue(line, SPELLED_DIGIT_PATTERN)).sum();
    return String.valueOf(total);
  }

  public int getCalibrationValue(String line, Pattern pattern) {
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      return Integer.parseInt(getDigit(matcher.group(1)) + getDigit(matcher.group(2)));
    }
    throw new NaughtyException("Unable to find calibration");
  }

  private String getDigit(String digit) {
    return SPELLED.getOrDefault(digit, digit);
  }
}
