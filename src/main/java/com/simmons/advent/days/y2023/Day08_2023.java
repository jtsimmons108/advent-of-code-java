package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.MathUtils;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day08_2023 extends AbstractDay {

  public static final Pattern NODE_PATTERN = Pattern.compile("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)");
  public static final String START = "AAA";
  public static final String END = "ZZZ";

  public static final char LEFT = 'L';

  public Day08_2023() {
    super(2023, 8);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    String instructions = lines.get(0);

    Map<String, Node> nodes = buildNodesFromLines(lines.subList(2, lines.size()));

    int steps = getRequiredSteps(START, instructions, nodes, s -> s.equals(END));
    return String.valueOf(steps);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    String instructions = lines.get(0);

    Map<String, Node> nodes = buildNodesFromLines(lines.subList(2, lines.size()));
    List<String> startingNodes =
        nodes.keySet().stream().filter(node -> node.charAt(2) == 'A').collect(Collectors.toList());

    long total = 1;
    for (String start : startingNodes) {
      int steps = getRequiredSteps(start, instructions, nodes, s -> s.charAt(2) == 'Z');
      total = MathUtils.lcm(total, steps);
    }

    return String.valueOf(total);
  }

  public int getRequiredSteps(
      String start, String instructions, Map<String, Node> nodes, Predicate<String> condition) {
    int steps = 0;
    String current = start;
    while (!condition.test(current)) {
      char move = instructions.charAt(steps % instructions.length());
      Node next = nodes.get(current);
      current = move == LEFT ? next.left : next.right;
      steps++;
    }
    return steps;
  }

  private Map<String, Node> buildNodesFromLines(List<String> lines) {
    Map<String, Node> nodes = new HashMap<>();
    for (String line : lines) {
      Matcher matcher = NODE_PATTERN.matcher(line);
      if (matcher.matches()) {
        nodes.put(matcher.group(1), new Node(matcher.group(2), matcher.group(3)));
      }
    }
    return nodes;
  }

  @Data
  @AllArgsConstructor
  class Node {
    String left;
    String right;
  }
}
