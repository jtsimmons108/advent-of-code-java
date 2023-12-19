package com.simmons.advent.days.y2023;

import static com.simmons.advent.grid.Direction.DOWN;
import static com.simmons.advent.grid.Direction.LEFT;
import static com.simmons.advent.grid.Direction.RIGHT;
import static com.simmons.advent.grid.Direction.UP;
import static com.simmons.advent.grid.GridUtils.cols;
import static com.simmons.advent.grid.GridUtils.rows;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.GridUtils;
import com.simmons.advent.grid.Location;
import com.simmons.advent.utils.InputUtils;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class Day17_2023 extends AbstractDay {

  public static final List<Direction> NEIGHBORS = List.of(UP, DOWN, LEFT, RIGHT);

  public Day17_2023() {
    super(2023, 17);
  }

  public String solvePartOne(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    int heatLoss = getMinimumHeatLoss(grid, 1, 3);
    return String.valueOf(heatLoss);
  }

  public String solvePartTwo(String input) {
    char[][] grid = InputUtils.getInputAsCharGrid(input);
    int heatLoss = getMinimumHeatLoss(grid, 4, 10);
    return String.valueOf(heatLoss);
  }

  public int getMinimumHeatLoss(char[][] grid, int minSteps, int maxSteps) {
    final int FINAL_ROW = rows(grid) - 1;
    final int FINAL_COL = cols(grid) - 1;
    Location start = Location.of(0, 0);
    Location finish = Location.of(FINAL_ROW, FINAL_COL);

    Set<State> seen = new HashSet<>();
    Queue<GridStep> queue = new PriorityQueue<>(Comparator.comparingInt(step -> step.heatLoss));
    queue.add(new GridStep(0, new State(start, RIGHT, 0)));
    queue.add(new GridStep(0, new State(start, DOWN, 0)));

    while (!queue.isEmpty()) {
      GridStep current = queue.poll();
      if (current.state.location.equals(finish) && current.state.consecutive >= minSteps) {
        return current.heatLoss;
      }
      if (!GridUtils.isValidLocation(current.state.location, grid)
          || seen.contains(current.state)) {
        continue;
      }

      seen.add(current.state);
      if (current.state.consecutive < maxSteps
          && GridUtils.isValidLocation(
              current.state.location.next(current.state.direction), grid)) {
        Location next = current.state.location.next(current.state.direction);
        queue.add(
            new GridStep(
                current.heatLoss + getHeatLoss(next, grid),
                new State(next, current.state.direction, current.state.consecutive + 1)));
      }
      if (current.state.consecutive >= minSteps) {
        Location left = current.state.location.next(current.state.direction.turnLeft());
        Location right = current.state.location.next(current.state.direction.turnRight());
        if (GridUtils.isValidLocation(left, grid)) {
          queue.add(
              new GridStep(
                  current.heatLoss + getHeatLoss(left, grid),
                  new State(left, current.state.direction.turnLeft(), 1)));
        }
        if (GridUtils.isValidLocation(right, grid)) {
          queue.add(
              new GridStep(
                  current.heatLoss + getHeatLoss(right, grid),
                  new State(right, current.state.direction.turnRight(), 1)));
        }
      }
    }
    throw new NaughtyException("Unable to reach destination");
  }

  private int getHeatLoss(Location location, char[][] grid) {
    return GridUtils.getValueFromLocation(location, grid) - '0';
  }

  @Data
  @AllArgsConstructor
  @EqualsAndHashCode
  class State {
    Location location;
    Direction direction;
    int consecutive;
  }

  @Data
  @AllArgsConstructor
  @EqualsAndHashCode
  class GridStep {
    int heatLoss;
    State state;
  }
}
