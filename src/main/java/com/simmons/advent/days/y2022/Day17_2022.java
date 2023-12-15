package com.simmons.advent.days.y2022;

import static com.simmons.advent.grid.Direction.*;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.Location;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day17_2022 extends AbstractDay {
  public static final int LEFT_START = 2;
  public static final int LEFT_BOUND = -1;
  public static final int RIGHT_BOUND = 7;
  public static final int PIECES = 5;

  public static final int HORIZONTAL_PIECE = 0;
  public static final int PLUS_PIECE = 1;
  public static final int L_PIECE = 2;
  public static final int VERTICAL_PIECE = 3;
  public static final int SQUARE_PIECE = 4;

  public static final Direction GRID_DOWN = UP; // Inverted from normal grid

  public static final long TARGET = 1000000000000L;

  public Day17_2022() {
    super(2022, 17);
  }

  public String solvePartOne(String input) {
    String jetStream = input;
    int step = 0;
    List<Rock> rocks = new ArrayList<>();
    Set<Location> resting =
        new HashSet<>(
            List.of( // Put in a floor
                Location.of(-1, 0),
                Location.of(-1, 1),
                Location.of(-1, 2),
                Location.of(-1, 3),
                Location.of(-1, 4),
                Location.of(-1, 5),
                Location.of(-1, 6)));

    for (int piece = 0; piece < 2022; piece++) {
      int startHeight = getHeight(resting) + 1 + 3;
      Rock nextRock = getNextPiece(piece, startHeight);
      rocks.add(nextRock);
      boolean canFall = true;
      while (canFall) {
        char move = jetStream.charAt(step++ % jetStream.length());
        Direction dir = move == '>' ? RIGHT : LEFT;
        if (nextRock.canMove(dir, resting)) {
          nextRock.move(dir);
        }
        canFall = nextRock.canMove(GRID_DOWN, resting);
        if (canFall) {
          nextRock.move(GRID_DOWN);
        }
      }
      resting.addAll(nextRock.squares);
    }

    int height = getHeight(resting) + 1;

    return String.valueOf(height);
  }

  public String solvePartTwo(String input) {
    String jetStream = input;
    List<Rock> rocks = new ArrayList<>();
    Set<Location> resting =
        new HashSet<>(
            List.of( // Put in a floor
                Location.of(-1, 0),
                Location.of(-1, 1),
                Location.of(-1, 2),
                Location.of(-1, 3),
                Location.of(-1, 4),
                Location.of(-1, 5),
                Location.of(-1, 6)));

    List<Integer> stageHeights = new ArrayList<>();
    Map<State, List<Integer>> statePieces = new HashMap<>();
    Map<State, List<Integer>> stateHeights = new HashMap<>();
    State prevState = null, currState = null;

    int step = 0;
    int piece = 0;
    boolean found = false;
    while (!found) {
      int startHeight = getHeight(resting) + 1 + 3;
      Rock nextRock = getNextPiece(piece++, startHeight);
      rocks.add(nextRock);
      prevState = currState;
      currState = new State(nextRock.getClass(), step % jetStream.length());

      List<Integer> pieces = statePieces.computeIfAbsent(currState, k -> new ArrayList<>());
      pieces.add(piece);
      if (pieces.size() == 2 && statePieces.get(prevState).size() == 2) {
        found = true;
      }

      boolean canFall = true;
      while (canFall) {
        char move = jetStream.charAt(step++ % jetStream.length());
        Direction dir = move == '>' ? RIGHT : LEFT;
        if (nextRock.canMove(dir, resting)) {
          nextRock.move(dir);
        }
        canFall = nextRock.canMove(GRID_DOWN, resting);
        if (canFall) {
          nextRock.move(GRID_DOWN);
        }
      }
      resting.addAll(nextRock.squares);
      int height = getHeight(resting);
      stateHeights.computeIfAbsent(currState, k -> new ArrayList<>()).add(height);
      stageHeights.add(height);
    }

    List<Integer> pieces = statePieces.get(currState);
    List<Integer> heights = stateHeights.get(currState);

    int startPiece = pieces.get(0);
    int startHeight = heights.get(0);

    int deltaPiece = pieces.get(1) - pieces.get(0);
    int deltaHeight = heights.get(1) - heights.get(0);

    long missingCycles = (TARGET - startPiece) / deltaPiece;
    long heightAtStartOfCycle = startHeight + missingCycles * deltaHeight;
    int remainingPieces = (int) (TARGET - (startPiece + missingCycles * deltaPiece));
    long finalHeight =
        heightAtStartOfCycle + stageHeights.get(startPiece + remainingPieces) - startHeight;
    return String.valueOf(finalHeight);
  }

  private int getHeight(Set<Location> resting) {
    return resting.stream().mapToInt(Location::getRow).max().getAsInt();
  }

  private void printRows(int height, Set<Location> resting) {
    for (int r = height; r >= 0; r--) {
      if (height - r >= 0) {
        for (int c = 0; c < 7; c++) {
          char val;
          if (resting.contains(Location.of(r, c))) {
            val = '#';
          } else {
            val = '.';
          }
          System.out.print(val);
        }
        System.out.println();
      }
    }
  }

  private Rock getNextPiece(int piece, int height) {
    switch (piece % PIECES) {
      case HORIZONTAL_PIECE:
        return new HorizontalRock(height);
      case PLUS_PIECE:
        return new PlusRock(height);
      case L_PIECE:
        return new LRock(height);
      case VERTICAL_PIECE:
        return new VerticalRock(height);
      case SQUARE_PIECE:
        return new SquareRock(height);
      default:
        throw new NaughtyException("Should never try to create a different rock");
    }
  }

  @Data
  @AllArgsConstructor
  class State {
    Class rockType;
    int jetStreamStep;
  }

  abstract class Rock {
    protected List<Location> squares;

    public Rock(int height) {
      this.squares = new ArrayList<>();
      buildSquares(height);
    }

    protected abstract void buildSquares(int height);

    public List<Location> next(Direction direction) {
      return squares.stream().map(square -> square.next(direction)).collect(Collectors.toList());
    }

    public void move(Direction direction) {
      squares = next(direction);
    }

    public boolean canMove(Direction direction, Set<Location> resting) {
      List<Location> next = next(direction);
      for (Location loc : next) {
        if (loc.col == LEFT_BOUND || loc.col == RIGHT_BOUND || resting.contains(loc)) {
          return false;
        }
      }
      return true;
    }

    public int getTopSquareHeight() {
      return this.squares.stream().mapToInt(Location::getRow).max().getAsInt() + 1;
    }
  }

  class HorizontalRock extends Rock {
    public HorizontalRock(int height) {
      super(height);
    }

    @Override
    protected void buildSquares(int height) {
      squares.addAll(
          List.of(
              Location.of(height, LEFT_START),
              Location.of(height, LEFT_START + 1),
              Location.of(height, LEFT_START + 2),
              Location.of(height, LEFT_START + 3)));
    }
  }

  class PlusRock extends Rock {
    public PlusRock(int height) {
      super(height);
    }

    @Override
    protected void buildSquares(int height) {
      squares.addAll(
          List.of(
              Location.of(height, LEFT_START + 1),
              Location.of(height + 1, LEFT_START),
              Location.of(height + 1, LEFT_START + 1),
              Location.of(height + 1, LEFT_START + 2),
              Location.of(height + 2, LEFT_START + 1)));
    }
  }

  class LRock extends Rock {
    public LRock(int height) {
      super(height);
    }

    @Override
    protected void buildSquares(int height) {
      squares.addAll(
          List.of(
              Location.of(height, LEFT_START),
              Location.of(height, LEFT_START + 1),
              Location.of(height, LEFT_START + 2),
              Location.of(height + 1, LEFT_START + 2),
              Location.of(height + 2, LEFT_START + 2)));
    }
  }

  class VerticalRock extends Rock {
    public VerticalRock(int height) {
      super(height);
    }

    @Override
    protected void buildSquares(int height) {
      squares.addAll(
          List.of(
              Location.of(height, LEFT_START),
              Location.of(height + 1, LEFT_START),
              Location.of(height + 2, LEFT_START),
              Location.of(height + 3, LEFT_START)));
    }
  }

  class SquareRock extends Rock {
    public SquareRock(int height) {
      super(height);
    }

    @Override
    protected void buildSquares(int height) {
      squares.addAll(
          List.of(
              Location.of(height, LEFT_START),
              Location.of(height, LEFT_START + 1),
              Location.of(height + 1, LEFT_START),
              Location.of(height + 1, LEFT_START + 1)));
    }
  }
}
