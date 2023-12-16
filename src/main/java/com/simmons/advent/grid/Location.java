package com.simmons.advent.grid;

import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Location location = (Location) o;
    return row == location.row && col == location.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
