package com.simmons.advent.grid;

public enum Direction {
  RIGHT(0, 1),
  LEFT(0, -1),
  UP(-1, 0),
  DOWN(1, 0),
  UP_LEFT(-1, -1),
  UP_RIGHT(-1, 1),
  DOWN_LEFT(1, -1),
  DOWN_RIGHT(1, 1);

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
