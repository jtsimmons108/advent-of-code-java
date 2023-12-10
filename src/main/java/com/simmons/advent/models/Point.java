package com.simmons.advent.models;

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

  public String toString() {
    return String.format("(%d, %d)", x, y);
  }
}
