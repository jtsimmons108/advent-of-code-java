package com.simmons.advent.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GridUtils {

  public static void printGrid(char[][] grid) {
    for (char[] line : grid) {
      for (char c : line) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static enum Direction {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    DOWN(1, 0);

    public final int dr;
    public final int dc;

    Direction(int dr, int dc) {
      this.dr = dr;
      this.dc = dc;
    }

    public Direction turnRight() {
      switch (this) {
        case RIGHT:
          return DOWN;
        case DOWN:
          return LEFT;
        case LEFT:
          return UP;
        default:
          return RIGHT;
      }
    }

    public Direction turnLeft() {
      switch (this) {
        case RIGHT:
          return UP;
        case UP:
          return LEFT;
        case LEFT:
          return DOWN;
        default:
          return RIGHT;
      }
    }
  }

  @Data
  @AllArgsConstructor
  public static class Location {
    public final int row;
    public final int col;
  }
}
