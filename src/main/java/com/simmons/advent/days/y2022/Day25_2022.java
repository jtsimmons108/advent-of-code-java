package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.List;
import java.util.Map;

public class Day25_2022 extends AbstractDay {

  public static final Map<Character, Long> DIGITS =
      Map.of('2', 2L, '1', 1L, '0', 0L, '-', -1L, '=', -2L);
  public static final Map<Long, Character> REVERSE_DIGITS =
      Map.of(2L, '2', 1L, '1', 0L, '0', -1L, '-', -2L, '=');

  public Day25_2022() {
    super(2022, 25);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    long sum = lines.stream().mapToLong(this::getDecimalFromSnafu).sum();
    return getSnafuFromDecimal(sum);
  }

  public String solvePartTwo(String input) {
    return "33";
  }

  public String getSnafuFromDecimal(long decimal) {
    int pow = 0;
    StringBuilder builder = new StringBuilder();
    while (decimal != 0) {
      long base = (long) Math.pow(5, pow);
      long dig = (decimal % (base * 5)) / base;
      if (dig > 2) {
        dig -= 5;
      }
      builder.insert(0, REVERSE_DIGITS.get(dig));
      decimal -= (long) Math.pow(5, pow) * dig;
      pow++;
    }
    return builder.toString();
  }

  public long getDecimalFromSnafu(String snafu) {
    long val = 0;
    int l = snafu.length();
    for (int i = 0; i < l; i++) {
      val += Math.pow(5, i) * DIGITS.get(snafu.charAt(l - i - 1));
    }
    return val;
  }
}
