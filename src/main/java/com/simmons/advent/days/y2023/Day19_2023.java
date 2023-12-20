package com.simmons.advent.days.y2023;

import com.simmons.advent.data.Tuple2;
import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.models.Range;
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
  public static final String INPUT_FLOW = "in";

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

    long total = 0;

    for (Part part : parts) {
      String result = workFlowMap.get(INPUT_FLOW).process(part, workFlowMap);
      if (ACCEPTED.equals(result)) {
        total += part.getSumOfParts();
      }
    }

    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<List<String>> grouped = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    Map<String, WorkFlow> workFlowMap =
        grouped.get(0).stream()
            .map(WorkFlow::new)
            .collect(Collectors.toMap(WorkFlow::getName, Function.identity()));

    PartRange start =
        new PartRange(Range.of(1, 4001), Range.of(1, 4001), Range.of(1, 4001), Range.of(1, 4001));
    long answer = workFlowMap.get(INPUT_FLOW).process(start, workFlowMap);
    return String.valueOf(answer);
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

    public long process(PartRange partRange, Map<String, WorkFlow> workFlowMap) {
      long total = 0;
      PartRange current = partRange;
      for (Rule rule : rules) {
        Tuple2<PartRange, PartRange> splitParts = rule.evaluate(current);
        if (ACCEPTED.equals(rule.returnFlow)) {
          long combinations = splitParts.first().getTotalCountFromRanges();
          total += combinations;
        } else if (!REJECTED.equals(rule.returnFlow)) {
          total += workFlowMap.get(rule.returnFlow).process(splitParts.first(), workFlowMap);
        }
        current = splitParts.second();
      }
      return total;
    }
  }

  @Data
  class Rule {
    Predicate<Part> condition;
    char testVal;
    char op;
    String returnFlow;
    long test;

    public Rule(String ruleString) {
      int colon = ruleString.indexOf(':');
      if (colon == -1) {
        condition = p -> true;
      } else {
        testVal = ruleString.charAt(0);
        op = ruleString.charAt(1);
        test = Long.parseLong(ruleString.substring(2, colon));
        condition = op == '>' ? p -> p.getPart(testVal) > test : p -> p.getPart(testVal) < test;
      }

      this.returnFlow = ruleString.substring(colon + 1);
    }

    public String evaluate(Part part) {
      return condition.test(part) ? returnFlow : null;
    }

    public Tuple2<PartRange, PartRange> evaluate(PartRange partRange) {
      if (op == 0) {
        return new Tuple2<>(partRange, null);
      } else {
        Tuple2<Range, Range> xRanges = getRangesForPart(partRange, 'x');
        Tuple2<Range, Range> mRanges = getRangesForPart(partRange, 'm');
        Tuple2<Range, Range> aRanges = getRangesForPart(partRange, 'a');
        Tuple2<Range, Range> sRanges = getRangesForPart(partRange, 's');

        return new Tuple2<>(
            new PartRange(xRanges.first(), mRanges.first(), aRanges.first(), sRanges.first()),
            new PartRange(xRanges.second(), mRanges.second(), aRanges.second(), sRanges.second()));
      }
    }

    private Tuple2<Range, Range> getRangesForPart(PartRange partRange, char val) {
      Range startRange = partRange.getRange(val);
      if (val == testVal) {
        return getAdjustedRanges(startRange, test, op == '<');
      }
      return new Tuple2<>(startRange, startRange);
    }

    private Tuple2<Range, Range> getAdjustedRanges(Range range, long val, boolean lessThan) {
      if (lessThan) {
        return new Tuple2<>(Range.of(range.start, val), Range.of(val, range.end));
      } else {
        return new Tuple2<>(Range.of(val + 1, range.end), Range.of(range.start, val + 1));
      }
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

  @Data
  class PartRange {
    Map<Character, Range> ranges = new HashMap<>();

    public PartRange(Range x, Range m, Range a, Range s) {
      ranges.put('x', x);
      ranges.put('m', m);
      ranges.put('a', a);
      ranges.put('s', s);
    }

    public Range getRange(char val) {
      return ranges.get(val);
    }

    public long getTotalCountFromRanges() {
      long total = 1;
      for (Range range : ranges.values()) {
        total *= range.length();
      }
      return total;
    }

    public String toString() {
      return ranges.toString();
    }
  }
}
