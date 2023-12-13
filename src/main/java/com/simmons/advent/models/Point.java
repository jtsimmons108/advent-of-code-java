package com.simmons.advent.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

  public static final Point ORIGIN = new Point();

  public static final Point of(long x, long y) {
    return new Point(x, y);
  }

  public final long x;
  public final long y;

  public Point() {
    this(0, 0);
  }

  public double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public long manhattanDistance(Point other) {
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
  }

  public Point next(Heading heading) {
    return Point.of(this.x + heading.dx, this.y + heading.dy);
  }

  public List<Point> neighbors() {
    return Arrays.stream(Heading.values()).map(this::next).collect(Collectors.toList());
  }

  public String toString() {
    return String.format("(%d, %d)", x, y);
  }
}
