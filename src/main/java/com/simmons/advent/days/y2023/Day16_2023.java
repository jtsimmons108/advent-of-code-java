package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.Direction.DOWN;
import static com.simmons.advent.grid.Direction.LEFT;
import static com.simmons.advent.grid.Direction.RIGHT;
import static com.simmons.advent.grid.Direction.UP;
import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day16_2023 extends AbstractDay {
  public static final Map<Character, Map<Direction, Direction>> REFLECTIONS =
      Map.of(
          '/',
          Map.of(UP, RIGHT, DOWN, LEFT, LEFT, DOWN, RIGHT, UP),
          '\\',
          Map.of(UP, LEFT, DOWN, RIGHT, LEFT, UP, RIGHT, DOWN));

  public Day16_2023() {
    super(2023, 16);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    int energized = getEnergizedLocations(new Beam(Location.of(0, 0), RIGHT), grid);
    return String.valueOf(energized);
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    final int ROWS = rows(grid);
    final int COLS = cols(grid);
    int maxEnergized = 0;
    for (int r = 0; r < ROWS; r++) {
      maxEnergized =
          Math.max(maxEnergized, getEnergizedLocations(new Beam(Location.of(r, 0), RIGHT), grid));
      maxEnergized =
          Math.max(
              maxEnergized, getEnergizedLocations(new Beam(Location.of(r, COLS - 1), LEFT), grid));
    }

    for (int c = 0; c < COLS; c++) {
      maxEnergized =
          Math.max(maxEnergized, getEnergizedLocations(new Beam(Location.of(0, c), DOWN), grid));
      maxEnergized =
          Math.max(
              maxEnergized, getEnergizedLocations(new Beam(Location.of(ROWS - 1, c), UP), grid));
    }

    return String.valueOf(maxEnergized);
  }

  public int getEnergizedLocations(Beam startBeam, char[][] grid) {
    Set<Location> visited = new HashSet<>();
    Set<String> states = new HashSet<>();
    Deque<Beam> beams = new ArrayDeque<>();
    beams.add(startBeam);
    while (beams.size() > 0) {
      Beam beam = beams.poll();
      if (!GridUtils.isValidLocation(beam.location, grid) || states.contains(beam.toString())) {
        continue;
      }
      states.add(beam.toString());
      visited.add(beam.location);

      char val = GridUtils.getValueFromLocation(beam.location, grid);
      boolean move = true;
      switch (val) {
        case '|':
          if (beam.direction == LEFT || beam.direction == RIGHT) {
            beams.add(new Beam(Location.of(beam.location.row + 1, beam.location.col), DOWN));
            beams.add(new Beam(Location.of(beam.location.row - 1, beam.location.col), UP));
            move = false;
          }
          break;
        case '-':
          if (beam.direction == UP || beam.direction == DOWN) {
            beams.add(new Beam(Location.of(beam.location.row, beam.location.col + 1), RIGHT));
            beams.add(new Beam(Location.of(beam.location.row, beam.location.col), LEFT));
            move = false;
          }
          break;
        case '/':
        case '\\':
          Direction next = REFLECTIONS.get(val).get(beam.direction);
          beam.direction = next;
      }

      if (move) {
        beam.location = beam.location.next(beam.direction);
        beams.add(beam);
      }
    }
    return visited.size();
  }

  @Data
  @AllArgsConstructor
  class Beam {
    Location location;
    Direction direction;
  }
}
