package com.simmons.advent.days.y2022;

import static com.simmons.advent.grid.Direction.*;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.grid.Direction;
import com.simmons.advent.grid.Location;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public Day17_2022() {
    super(2022, 17);
  }

  public String solvePartOne(String input) {
    String jetStream = input;
    int step = 0;
    List<Rock> rocks = new ArrayList<>();
    Set<Location> resting = new HashSet<>();
    for (int col = 0; col < 7; col++) {
      resting.add(Location.of(-1, col));
    }

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

  // Solved manually for now. Will code a solution later.

  public String solvePartTwo(String input) {
    String jetStream = input;
    int step = 0;
    int patternStep = 0;
    List<Rock> rocks = new ArrayList<>();
    Set<Location> resting = new HashSet<>();
    for (int col = 0; col < 7; col++) {
      resting.add(Location.of(-1, col));
    }

    for (int piece = 0; piece < 10000; piece++) {
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
      int height = getHeight(resting);

      if ((piece - 1755) % 1745 == 0 || (piece - 1755) % 1745 == 1000) {
        System.out.printf("After height %d tallest height is %d%n", piece, height);
      }
    }

    int height = getHeight(resting) + 1;

    return String.valueOf(height);
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
