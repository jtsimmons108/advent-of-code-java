package com.simmons.advent.days.y2022;

import com.simmons.advent.data.CircularLinkedList;
import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day20_2022 extends AbstractDay {

  public Day20_2022() {
    super(2022, 20);
  }

  public String solvePartOne(String input) {
    List<Integer> ints = InputUtils.getInputsAsIntList(input);
    long coordinate = getGroveCoordinates(mix(ints, 1L, 1));
    return String.valueOf(coordinate);
  }

  public String solvePartTwo(String input) {
    List<Integer> ints = InputUtils.getInputsAsIntList(input);
    long coordinate = getGroveCoordinates(mix(ints, 811589153L, 10));
    return String.valueOf(coordinate);
  }

  public long getGroveCoordinates(List<Long> coordinates) {
    int zeroIndex = coordinates.indexOf(0L);
    int size = coordinates.size();
    return coordinates.get((zeroIndex + 1000) % size)
        + coordinates.get((zeroIndex + 2000) % size)
        + coordinates.get((zeroIndex + 3000) % size);
  }

  public List<Long> mix(List<Integer> numbers, long multiplier, int rounds) {
    List<EncryptedNumber> encrypted =
        IntStream.range(0, numbers.size())
            .mapToObj(i -> new EncryptedNumber(numbers.get(i) * multiplier, i))
            .collect(Collectors.toList());
    CircularLinkedList<EncryptedNumber> circularList = new CircularLinkedList<>(encrypted);
    for (int round = 0; round < rounds; round++) {
      for (EncryptedNumber num : encrypted) {
        shift(circularList, num);
      }
    }
    return circularList.toList().stream().map(e -> e.val).collect(Collectors.toList());
  }

  private void shift(CircularLinkedList<EncryptedNumber> list, EncryptedNumber value) {
    int n = list.size() - 1;
    int index = list.indexOf(value);
    list.removeAtIndex(index);
    int newIndex = (int) ((index + value.val) % n + n) % n;
    if (newIndex == 0 && value.val < 0) {
      newIndex = n;
    }
    list.insert(newIndex, value);
  }

  @Data
  @AllArgsConstructor
  class EncryptedNumber {
    long val;
    int originalIndex;
  }
}
