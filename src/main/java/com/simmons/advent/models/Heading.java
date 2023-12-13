package com.simmons.advent.models;

import java.util.List;
import java.util.Map;

public enum Heading {
  NORTH(0, 1),
  NORTHEAST(1, 1),
  NORTHWEST(-1, 1),
  SOUTH(0, -1),
  SOUTHEAST(1, -1),
  SOUTHWEST(-1, -1),
  EAST(1, 0),
  WEST(-1, 0);

  public static final Map<Heading, List<Heading>> grouped =
      Map.of(
          NORTH,
          List.of(NORTH, NORTHEAST, NORTHWEST),
          SOUTH,
          List.of(SOUTH, SOUTHEAST, SOUTHWEST),
          WEST,
          List.of(WEST, SOUTHWEST, NORTHWEST),
          EAST,
          List.of(EAST, NORTHEAST, SOUTHEAST));
  final int dx, dy;

  Heading(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }
}
