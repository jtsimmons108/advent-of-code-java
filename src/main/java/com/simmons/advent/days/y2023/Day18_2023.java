package com.simmons.advent.days.y2023;

import static com.simmons.advent.models.Heading.EAST;
import static com.simmons.advent.models.Heading.NORTH;
import static com.simmons.advent.models.Heading.SOUTH;
import static com.simmons.advent.models.Heading.WEST;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.Heading;
import com.simmons.advent.models.Point;
import com.simmons.advent.utils.InputUtils;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day18_2023 extends AbstractDay {

  public static final Pattern LINE_PATTERN =
      Pattern.compile("([RUDL]) (\\d+) \\(#([0-9a-f]{6})\\)");

  public static final String[] DIRECTIONS = {"R", "D", "L", "U"};
  public static final Map<String, Heading> HEADINGS =
      Map.of("R", EAST, "U", NORTH, "D", SOUTH, "L", WEST);

  public Day18_2023() {
    super(2023, 18);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Point> vertices = getVerticesFromInstructions(getInstructionList(lines), true);
    long area = getAreaFromVertices(vertices);
    return String.valueOf(area);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Point> vertices = getVerticesFromInstructions(getInstructionList(lines), false);
    long area = getAreaFromVertices(vertices);
    return String.valueOf(area);
  }

  private List<Point> getVerticesFromInstructions(List<Instruction> instructions, boolean part1) {
    Point current = Point.ORIGIN;
    List<Point> vertices = new ArrayList<>();
    vertices.add(current);
    for (Instruction ins : instructions) {
      Heading heading =
          part1
              ? HEADINGS.get(ins.heading)
              : HEADINGS.get(DIRECTIONS[ins.color.charAt(ins.color.length() - 1) - '0']);
      long length =
          part1 ? ins.length : Long.parseLong(ins.color.substring(0, ins.color.length() - 1), 16);
      current = Point.of(current.x + heading.dx * length, current.y + heading.dy * length);
      vertices.add(current);
    }

    return vertices;
  }

  private long getAreaFromVertices(List<Point> vertices) {
    long signedArea = 0;
    long perimeter = 0;
    for (int i = 0; i < vertices.size(); i++) {
      Point first = vertices.get(i);
      Point second = vertices.get((i + 1) % vertices.size());
      signedArea += first.x * second.y - second.x * first.y;
      perimeter += Math.abs(first.x - second.x) + Math.abs(first.y - second.y);
    }
    return Math.abs(signedArea) / 2 + perimeter / 2 + 1;
  }

  private List<Instruction> getInstructionList(List<String> lines) {
    List<Instruction> instructions = new ArrayList<>();
    for (String line : lines) {
      Matcher matcher = LINE_PATTERN.matcher(line);
      if (matcher.matches()) {
        instructions.add(
            new Instruction(
                matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(3)));
      }
    }
    return instructions;
  }

  @Data
  @AllArgsConstructor
  class Instruction {
    String heading;
    int length;
    String color;
  }
}
