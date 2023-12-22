package com.simmons.advent.days.y2018;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class Day25_2018 extends AbstractDay {

  public Day25_2018() {
    super(2018, 25);
  }

  public String solvePartOne(String input) {
    List<Point4D> points =
        InputUtils.getInputAsList(input).stream().map(Point4D::new).collect(Collectors.toList());
    Collections.sort(points, Comparator.comparingLong(Point4D::distanceFromOrigin));
    List<Set<Point4D>> neighborhoods = new ArrayList<>();
    for (Point4D point : points) {
      List<Set<Point4D>> matches = new ArrayList<>();
      for (Set<Point4D> neighborhood : neighborhoods) {
        if (neighborhood.stream().anyMatch(p -> point.manhattanDistance(p) <= 3)) {
          matches.add(neighborhood);
        }
      }

      if (matches.size() > 1) {
        Set<Point4D> newNeighborhood = new HashSet<>();
        for (Set<Point4D> match : matches) {
          newNeighborhood.addAll(match);
          neighborhoods.remove(match);
        }
        newNeighborhood.add(point);
        neighborhoods.add(newNeighborhood);
      } else if (matches.size() == 1) {
        matches.get(0).add(point);
      } else {
        Set<Point4D> newNeighborhood = new HashSet<>();
        newNeighborhood.add(point);
        neighborhoods.add(newNeighborhood);
      }
    }

    return String.valueOf(neighborhoods.size());
  }

  public String solvePartTwo(String input) {
    return "38";
  }

  @Data
  @EqualsAndHashCode
  class Point4D {
    final long x, y, z, w;

    public Point4D(String line) {
      List<Long> ints = StringUtils.extractIntsFromString(line, true);
      this.x = ints.get(0);
      this.y = ints.get(1);
      this.z = ints.get(2);
      this.w = ints.get(3);
    }

    public long manhattanDistance(Point4D other) {
      return Math.abs(this.x - other.x)
          + Math.abs(this.y - other.y)
          + Math.abs(this.z - other.z)
          + Math.abs(this.w - other.w);
    }

    public long distanceFromOrigin() {
      return Math.abs(x) + Math.abs(y) + Math.abs(z) + Math.abs(w);
    }

    public String toString() {
      return String.format("(%d, %d, %d, %d)", x, y, z, w);
    }
  }
}
