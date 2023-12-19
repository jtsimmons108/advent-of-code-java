package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Data;

public class Day19_2023 extends AbstractDay {

  public static final String ACCEPTED = "A";
  public static final String REJECTED = "R";

  public Day19_2023() {
    super(2023, 19);
  }

  public String solvePartOne(String input) {
    List<List<String>> grouped = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    Map<String, WorkFlow> workFlowMap =
        grouped.get(0).stream()
            .map(WorkFlow::new)
            .collect(Collectors.toMap(WorkFlow::getName, Function.identity()));

    List<Part> parts =
        grouped.get(1).stream()
            .map(
                line -> {
                  List<Long> nums = StringUtils.extractIntsFromString(line, false);
                  return new Part(nums.get(0), nums.get(1), nums.get(2), nums.get(3));
                })
            .collect(Collectors.toList());

    System.out.println(parts);

    return "";
  }

  public String solvePartTwo(String input) {
    return null;
  }

  @Data
  class WorkFlow {
    List<Rule> rules;
    String name;

    public WorkFlow(String line) {
      buildWorkFlowFromInput(line);
    }

    private void buildWorkFlowFromInput(String line) {
      int bracket = line.indexOf('{');
      this.name = line.substring(0, bracket);
      this.rules =
          Arrays.stream(line.substring(bracket + 1, line.length() - 1).split(","))
              .map(Rule::new)
              .collect(Collectors.toList());
    }

    public String process(Part part, Map<String, WorkFlow> workFlowMap) {
      for (Rule rule : rules) {
        String result = rule.evaluate(part);
        if (ACCEPTED.equals(result) || REJECTED.equals(result)) {
          return result;
        } else if (result != null) {
          return workFlowMap.get(result).process(part, workFlowMap);
        }
      }
      throw new NaughtyException("Should always find a result");
    }
  }

  @Data
  class Rule {
    Predicate<Part> condition;
    char testVal;
    String returnFlow;
    long test;

    public Rule(String ruleString) {
      int colon = ruleString.indexOf(':');
      if (colon == -1) {
        condition = p -> true;
      } else {

        testVal = ruleString.charAt(0);
        char op = ruleString.charAt(1);
        this.test = Long.parseLong(ruleString.substring(2, colon));
        condition = op == '>' ? p -> p.getPart(testVal) > test : p -> p.getPart(testVal) < test;
      }

      this.returnFlow = ruleString.substring(colon + 1);
    }

    public String evaluate(Part part) {
      return condition.test(part) ? returnFlow : null;
    }
  }

  @Data
  class Part {
    Map<Character, Long> values = new HashMap<>();

    public Part(long x, long m, long a, long s) {
      values.put('x', x);
      values.put('m', m);
      values.put('a', a);
      values.put('s', s);
    }

    public long getPart(char val) {
      return values.get(val);
    }

    public long getSumOfParts() {
      return values.get('x') + values.get('m') + values.get('a') + values.get('s');
    }
  }
}
