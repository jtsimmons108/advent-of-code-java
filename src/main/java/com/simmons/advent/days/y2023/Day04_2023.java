package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.*;
import java.util.stream.Collectors;

public class Day04_2023 extends AbstractDay {

  public Day04_2023() {
    super(2023, 4);
  }

  public String solvePartOne(String input) {
    int total =
        InputUtils.getInputAsList(input).stream()
            .mapToInt(this::getWinningNumbers)
            .map(n -> (int) Math.pow(2, n - 1))
            .sum();
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Integer> winnings =
        lines.stream().map(this::getWinningNumbers).collect(Collectors.toList());
    int[] cards = new int[winnings.size()];
    Arrays.fill(cards, 1);
    for (int i = 0; i < winnings.size(); i++) {
      for (int j = 0; j < winnings.get(i); j++) {
        cards[i + j + 1] += cards[i];
      }
    }
    return String.valueOf(Arrays.stream(cards).sum());
  }

  public int getWinningNumbers(String line) {
    int colon = line.indexOf(':');
    int vert = line.indexOf('|');
    Set<Long> winningNumbers =
        new HashSet<>(StringUtils.extractIntsFromString(line.substring(colon, vert), false));
    Set<Long> cardNumbers =
        new HashSet<>(StringUtils.extractIntsFromString(line.substring(vert), false));
    winningNumbers.retainAll(cardNumbers);
    return winningNumbers.size();
  }
}
