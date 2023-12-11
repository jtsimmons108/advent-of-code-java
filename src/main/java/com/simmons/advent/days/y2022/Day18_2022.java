package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

public class Day18_2022 extends AbstractDay {

  public Day18_2022() {
    super(2022, 18);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Cube> cubes = new ArrayList<>();

    for (String line : lines) {
      cubes.add(new Cube(line));
    }

    long total = cubes.size() * 6;
    for (int i = 0; i < cubes.size(); i++) {
      for (int j = i + 1; j < cubes.size(); j++) {
        if (cubes.get(i).sharesSideWith(cubes.get(j))) {
          total -= 2;
        }
      }
    }

    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    return null;
  }

  @Data
  class Cube {
    final int x, y, z;

    public Cube(String xyz) {
      String[] split = xyz.split(",");
      x = Integer.parseInt(split[0]);
      y = Integer.parseInt(split[1]);
      z = Integer.parseInt(split[2]);
    }

    public boolean sharesSideWith(Cube other) {
      int count = 0;
      if (this.x == other.x) {
        count++;
      }
      if (this.y == other.y) {
        count++;
      }
      if (this.z == other.z) {
        count++;
      }
      return count == 2 && Math.abs(this.x - other.x + this.y - other.y + this.z - other.z) == 1;
    }
  }
}
