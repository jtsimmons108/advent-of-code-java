package com.simmons.advent.days.y2022;

import static com.simmons.advent.models.Heading.*;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.Heading;
import com.simmons.advent.models.Point;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Data;

public class Day23_2022 extends AbstractDay {

  public static final List<Heading> ORDER = List.of(NORTH, SOUTH, WEST, EAST);

  public Day23_2022() {
    super(2022, 23);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Elf> elves = getElvesFromInput(lines);

    for (int i = 0; i < 10; i++) {
      playRound(elves, i);
    }

    long minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
    long minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
    for (Elf elf : elves) {
      minX = Math.min(minX, elf.location.x);
      maxX = Math.max(maxX, elf.location.x);
      minY = Math.min(minY, elf.location.y);
      maxY = Math.max(maxY, elf.location.y);
    }
    long area = (maxX - minX + 1) * (maxY - minY + 1);
    return String.valueOf(area - elves.size());
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Elf> elves = getElvesFromInput(lines);
    int round = 0;
    while (playRound(elves, round++))
      ;
    return String.valueOf(round);
  }

  private List<Elf> getElvesFromInput(List<String> lines) {
    List<Elf> elves = new ArrayList<>();
    for (int r = 0; r < lines.size(); r++) {
      String line = lines.get(r);
      for (int c = 0; c < lines.get(0).length(); c++) {
        if (line.charAt(c) == '#') {
          elves.add(new Elf(Point.of(c, lines.size() - 1 - r)));
        }
      }
    }
    return elves;
  }

  private boolean playRound(List<Elf> elves, int round) {
    Set<Point> currentLocations = elves.stream().map(Elf::getLocation).collect(Collectors.toSet());
    ;
    Map<Point, List<Elf>> proposals = new HashMap<>();
    for (Elf elf : elves) {
      if (elf.canMove(currentLocations)) {
        Point to = elf.proposePoint(currentLocations, round);
        if (to != null) {
          List<Elf> list = proposals.computeIfAbsent(to, k -> new ArrayList<>());
          list.add(elf);
        }
      }
    }
    boolean moved = false;
    for (Map.Entry<Point, List<Elf>> entry : proposals.entrySet()) {
      if (entry.getValue().size() == 1) {
        entry.getValue().get(0).location = entry.getKey();
        moved = true;
      }
    }
    return moved;
  }

  @Data
  class Elf {
    Point location;

    public Elf(Point location) {
      this.location = location;
    }

    public boolean canMove(Set<Point> locations) {
      return location.neighbors().stream().anyMatch(locations::contains);
    }

    public Point proposePoint(Set<Point> locations, int round) {
      for (int i = 0; i < 4; i++) {
        Heading heading = ORDER.get((round + i) % 4);
        if (!grouped.get(heading).stream().anyMatch(h -> locations.contains(location.next(h)))) {
          return location.next(heading);
        }
      }
      return null;
    }
  }
}
