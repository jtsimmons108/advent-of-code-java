package com.simmons.advent.grid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
  public final int row;
  public final int col;

  public static final Location of(int row, int col) {
    return new Location(row, col);
  }

  public Location next(Direction direction) {
    return Location.of(this.row + direction.dr, this.col + direction.dc);
  }
}
