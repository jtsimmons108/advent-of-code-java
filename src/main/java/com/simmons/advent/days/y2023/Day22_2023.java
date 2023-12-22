package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.Range;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Data;

public class Day22_2023 extends AbstractDay {

  public Day22_2023() {
    super(2023, 22);
  }

  public String solvePartOne(String input) {
    List<Cube> cubes =
        InputUtils.getInputAsList(input).stream().map(Cube::new).collect(Collectors.toList());
    cubes.sort(Comparator.comparingLong(c -> c.zRange.start));
    int index = 0;
    while (cubes.get(index).zRange.start == 1) {
      index++;
    }

    while (index < cubes.size()) {
      Cube current = cubes.get(index);
      while (current.zRange.start != 1
          && !cubes.subList(0, index).stream().anyMatch(c -> c.supports(current))) {
        current.zRange = Range.of(current.zRange.start - 1, current.zRange.end - 1);
      }
      index++;
    }

    Map<Integer, Set<Integer>> supports = new HashMap<>();
    Map<Integer, Set<Integer>> supportedBy = new HashMap<>();

    for (int i = 0; i < cubes.size(); i++) {
      for (int j = i + 1; j < cubes.size(); j++) {
        Cube first = cubes.get(i);
        Cube second = cubes.get(j);
        if (first.supports(second)) {
          supports.computeIfAbsent(first.id, k -> new HashSet<>()).add(second.id);
          supportedBy.computeIfAbsent(second.id, k -> new HashSet<>()).add(first.id);
        }
      }
    }

    int total = 0;
    for (int i = 0; i < cubes.size(); i++) {
      Set<Integer> currentSupports = supports.get(i);
      if (currentSupports == null
          || currentSupports.stream().allMatch(b -> supportedBy.get(b).size() > 1)) {
        total++;
      }
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<Cube> cubes =
        InputUtils.getInputAsList(input).stream().map(Cube::new).collect(Collectors.toList());
    cubes.sort(Comparator.comparingLong(c -> c.zRange.start));
    int index = 0;
    while (cubes.get(index).zRange.start == 1) {
      index++;
    }

    while (index < cubes.size()) {
      Cube current = cubes.get(index);
      while (current.zRange.start != 1
          && !cubes.subList(0, index).stream().anyMatch(c -> c.supports(current))) {
        current.zRange = Range.of(current.zRange.start - 1, current.zRange.end - 1);
      }
      index++;
    }

    Map<Integer, Set<Integer>> supports = new HashMap<>();
    Map<Integer, Set<Integer>> supportedBy = new HashMap<>();
    for (int i = 0; i < cubes.size(); i++) {
      for (int j = i + 1; j < cubes.size(); j++) {
        Cube first = cubes.get(i);
        Cube second = cubes.get(j);
        if (first.supports(second)) {
          supports.computeIfAbsent(first.id, k -> new HashSet<>()).add(second.id);
          supportedBy.computeIfAbsent(second.id, k -> new HashSet<>()).add(first.id);
        }
      }
    }

    int count = 0;
    for (int i = 0; i < cubes.size(); i++) {
      count += getMovedBricksIfRemoved(cubes, i);
    }
    return String.valueOf(count);
  }

  private int getMovedBricksIfRemoved(List<Cube> cubes, int id) {
    List<Cube> remainingCubes = new ArrayList<>(cubes.size());
    for (int i = 0; i < cubes.size(); i++) {
      if (id != i) {
        remainingCubes.add(new Cube(cubes.get(i)));
      }
    }

    int total = 0;
    for (int i = 0; i < remainingCubes.size(); i++) {
      Cube current = remainingCubes.get(i);
      boolean moved = false;
      while (current.zRange.start != 1
          && !remainingCubes.subList(0, i).stream().anyMatch(c -> c.supports(current))) {
        current.zRange = Range.of(current.zRange.start - 1, current.zRange.end - 1);
        moved = true;
      }
      total += moved ? 1 : 0;
    }
    return total;
  }
}

@Data
class Cube {
  public static int current = 0;
  Range xRange, yRange, zRange;
  int id;

  public Cube(Cube other) {
    this(
        other.id,
        Range.of(other.xRange.start, other.xRange.end),
        Range.of(other.yRange.start, other.yRange.end),
        Range.of(other.zRange.start, other.zRange.end));
  }

  public Cube(int id, Range xRange, Range yRange, Range zRange) {
    this.id = id;
    this.xRange = xRange;
    this.yRange = yRange;
    this.zRange = zRange;
  }

  public Cube(String line) {
    List<Long> coords = StringUtils.extractIntsFromString(line, false);
    xRange = Range.of(coords.get(0), coords.get(3) + 1);
    yRange = Range.of(coords.get(1), coords.get(4) + 1);
    zRange = Range.of(coords.get(2), coords.get(5) + 1);
    id = current++;
  }

  public boolean supports(Cube other) {
    return this.xRange.intersects(other.xRange)
        && this.yRange.intersects(other.yRange)
        && this.zRange.intersects(Range.of(other.zRange.start - 1, other.zRange.end - 1));
  }

  public String toString() {
    return String.format("Cube %d: %s, %s, %s", id, xRange, yRange, zRange);
  }
}
