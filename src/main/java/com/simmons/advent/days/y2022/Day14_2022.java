package com.simmons.advent.days.y2022;

import static com.simmons.advent.grid.Direction.DOWN;
import static com.simmons.advent.grid.Direction.LEFT;
import static com.simmons.advent.grid.Direction.RIGHT;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day14_2022 extends AbstractDay {

  public static final Location START = Location.of(0, 500);

  public Day14_2022() {
    super(2022, 14);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    Set<Location> rocks = getRocksFromLines(lines);
    Set<Location> sand = new HashSet<>();
    int abyss = rocks.stream().mapToInt(Location::getRow).max().getAsInt();
    boolean flowing = true;
    while (flowing) {
      Location restLocation = fall(rocks, sand, abyss);
      if (restLocation.row != abyss) {
        sand.add(restLocation);
      } else {
        flowing = false;
      }
    }
    return String.valueOf(sand.size());
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    Set<Location> rocks = getRocksFromLines(lines);
    Set<Location> sand = new HashSet<>();
    int abyss = rocks.stream().mapToInt(Location::getRow).max().getAsInt() + 1;
    while (!sand.contains(START)) {
      Location restLocation = fall(rocks, sand, abyss);
      sand.add(restLocation);
    }
    return String.valueOf(sand.size());
  }

  private Set<Location> getRocksFromLines(List<String> lines) {
    Set<Location> rocks = new HashSet<>();
    for (String line : lines) {
      String[] split = line.split(" -> ");
      List<Location> locations =
          Arrays.stream(split)
              .map(
                  s -> {
                    String[] ss = s.split(",");
                    return Location.of(Integer.parseInt(ss[1]), Integer.parseInt(ss[0]));
                  })
              .collect(Collectors.toList());

      for (int i = 0; i < locations.size() - 1; i++) {
        Location from = locations.get(i);
        Location to = locations.get(i + 1);
        int dr = from.row == to.row ? 0 : ((to.row - from.row) / Math.abs(from.row - to.row));
        int dc = from.col == to.col ? 0 : ((to.col - from.col) / Math.abs(from.col - to.col));
        Location current = from;
        while (!current.equals(to)) {
          rocks.add(current);
          current = Location.of(current.row + dr, current.col + dc);
        }
        rocks.add(current);
      }
    }
    return rocks;
  }

  private Location fall(Set<Location> rocks, Set<Location> sand, int abyss) {
    return fall(START, rocks, sand, abyss);
  }

  private Location fall(
      Location startLocation, Set<Location> rocks, Set<Location> sand, int abyss) {
    Location current = startLocation;
    Location next = current.next(DOWN);
    while (!rocks.contains(next) && !sand.contains(next)) {
      if (current.row == abyss) {
        return current;
      }
      current = next;
      next = current.next(DOWN);
    }
    if (!isBlocked(current.next(DOWN).next(LEFT), rocks, sand)) {
      return fall(current.next(DOWN).next(LEFT), rocks, sand, abyss);
    }
    if (!isBlocked(current.next(DOWN).next(RIGHT), rocks, sand)) {
      return fall(current.next(DOWN).next(RIGHT), rocks, sand, abyss);
    }
    return current;
  }

  private boolean isBlocked(Location location, Set<Location> rocks, Set<Location> sand) {
    return rocks.contains(location) || sand.contains(location);
  }
}
