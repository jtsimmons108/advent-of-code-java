package com.simmons.advent.days.y2022;

import static com.simmons.advent.grid.Direction.*;
import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class Day24_2022 extends AbstractDay {

  public static final int ROUNDS = 750;
  public static final char WALL = '#';

  public static final List<Direction> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);

  public Day24_2022() {
    super(2022, 24);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    final int ROWS = rows(grid);
    final int COLS = cols(grid);
    Location start = Location.of(0, 1);
    Location finish = Location.of(ROWS - 1, COLS - 2);
    List<Snowflake> snowflakes = getAllSnowflakes(grid);
    List<Set<Location>> stages = getSnowflakeLocations(snowflakes, ROUNDS);
    int answer = getTimeFrom(start, finish, 0, grid, stages);
    return String.valueOf(answer);
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    final int ROWS = rows(grid);
    final int COLS = cols(grid);
    Location start = Location.of(0, 1);
    Location finish = Location.of(ROWS - 1, COLS - 2);
    List<Snowflake> snowflakes = getAllSnowflakes(grid);
    List<Set<Location>> stages = getSnowflakeLocations(snowflakes, ROUNDS);
    int first = getTimeFrom(start, finish, 0, grid, stages);
    int second = getTimeFrom(finish, start, first, grid, stages);
    int last = getTimeFrom(start, finish, second, grid, stages);
    return String.valueOf(last);
  }

  private int getTimeFrom(
      Location start, Location finish, int startTime, char[][] grid, List<Set<Location>> stages) {
    Set<Location> walls = new HashSet<>(GridUtils.getAllLocations(grid, WALL));
    Set<Step> seen = new HashSet<>();
    Queue<Step> steps = new PriorityQueue<>(Comparator.comparing(s -> s.minute));
    steps.add(new Step(start, startTime));
    while (true) {
      Step step = steps.poll();
      if (seen.contains(step)) {
        continue;
      }
      seen.add(step);
      if (step.location.equals(finish)) {
        return step.minute;
      }
      Location loc = step.location;
      Set<Location> occupied = stages.get(step.minute + 1);
      if (!occupied.contains(loc)) {
        steps.add(new Step(loc, step.minute + 1));
      }

      for (Direction direction : DIRECTIONS) {
        Location next = loc.next(direction);
        if (!walls.contains(next)
            && !occupied.contains(next)
            && GridUtils.isValidLocation(next, grid)) {
          steps.add(new Step(next, step.minute + 1));
        }
      }
    }
  }

  private List<Set<Location>> getSnowflakeLocations(List<Snowflake> snowflakes, int rounds) {
    List<Set<Location>> stages = new ArrayList<>(rounds);
    for (int i = 0; i < rounds; i++) {
      stages.add(snowflakes.stream().map(Snowflake::getLocation).collect(Collectors.toSet()));
      snowflakes.stream().forEach(Snowflake::move);
    }
    return stages;
  }

  private List<Snowflake> getAllSnowflakes(char[][] grid) {
    final int ROWS = rows(grid);
    final int COLS = cols(grid);
    List<Snowflake> snowflakes = new ArrayList<>(ROWS * COLS);
    for (int r = 0; r < ROWS; r++) {
      for (int c = 0; c < COLS; c++) {
        char val = grid[r][c];
        Location loc = Location.of(r, c);
        Snowflake snowflake = null;
        switch (val) {
          case '^':
            snowflake = new Snowflake(loc, UP, ROWS, COLS);
            break;
          case '>':
            snowflake = new Snowflake(loc, RIGHT, ROWS, COLS);
            break;
          case 'v':
            snowflake = new Snowflake(loc, DOWN, ROWS, COLS);
            break;
          case '<':
            snowflake = new Snowflake(loc, LEFT, ROWS, COLS);
            break;
        }
        if (snowflake != null) {
          snowflakes.add(snowflake);
        }
      }
    }
    return snowflakes;
  }

  @Data
  @AllArgsConstructor
  class Snowflake {
    Location location;
    Direction direction;
    int rows, cols;

    public void move() {
      Location next = location.next(direction);
      if (next.row == 0) {
        next = Location.of(rows - 2, next.col);
      } else if (next.row == rows - 1) {
        next = Location.of(1, next.col);
      } else if (next.col == 0) {
        next = Location.of(next.row, cols - 2);
      } else if (next.col == cols - 1) {
        next = Location.of(next.row, 1);
      }
      location = next;
    }
  }

  @Data
  @AllArgsConstructor
  @EqualsAndHashCode
  class Step {
    Location location;
    int minute;
  }
}
