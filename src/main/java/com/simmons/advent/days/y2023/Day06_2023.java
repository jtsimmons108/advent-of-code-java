package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.List;

public class Day06_2023 extends AbstractDay {

  public Day06_2023() {
    super(2023, 6);
  }

  public String solvePartOne(String input) {

    List<String> lines = InputUtils.getInputAsList(input);
    List<Long> times = StringUtils.extractIntsFromString(lines.get(0), false);
    List<Long> distances = StringUtils.extractIntsFromString(lines.get(1), false);

    long total = 1;
    for (int race = 0; race < times.size(); race++) {
      long winnings = getWinningCombos(times.get(race), distances.get(race));
      total *= winnings;
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    String time = lines.get(0).split("\\s+", 2)[1].replaceAll("\\s+", "");
    String distance = lines.get(1).split("\\s+", 2)[1].replaceAll("\\s+", "");
    long combos = getWinningCombos(Long.parseLong(time), Long.parseLong(distance));
    return String.valueOf(combos);
  }

  /*
     Get winning combinations for number of moves.
     Need:  t * (time - t) > distance
     where 0 <= t <= time
     Therefore, need -t ** 2 + time * t - distance > 0 or
     t ** 2 - time * t + distance < 0
     Since this is a concave-up parabola, the time values
     are located between the 2 x-intercepts
  */
  public long getWinningCombos(long time, long distance) {
    double discriminant = Math.sqrt(time * time - 4 * distance);
    long left = (long) Math.floor((time - discriminant) / 2) + 1;
    long right = (long) Math.ceil((time + discriminant) / 2) - 1;
    return right - left + 1;
  }
}
