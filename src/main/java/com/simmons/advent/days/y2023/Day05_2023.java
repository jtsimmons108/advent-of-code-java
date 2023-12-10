package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.Range;
import com.simmons.advent.models.RegexMatch;
import com.simmons.advent.utils.InputUtils;
import com.simmons.advent.utils.PatternUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day05_2023 extends AbstractDay {

  public static List<String> elements =
      List.of("seed", "soil", "fertilizer", "water", "light", "temperature", "humidity");

  public Day05_2023() {
    super(2023, 5);
  }

  public String solvePartOne(String input) {

    List<List<String>> lines = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    Map<String, List<MappedRange>> ranges = new HashMap<>();
    buildRangesFromInput(lines, ranges);
    long min = Long.MAX_VALUE;
    List<RegexMatch> matches =
        PatternUtils.getAllMatchesFromInput(lines.get(0).get(0), PatternUtils.INT_PATTERN);
    for (RegexMatch match : matches) {
      min = Math.min(min, getLocationFromSeed(Long.parseLong(match.getValue()), ranges));
    }

    return String.valueOf(min);
  }

  public String solvePartTwo(String input) {
    List<List<String>> lines = InputUtils.getInputAs2DList(input, "\n\n", "\n");
    Map<String, List<MappedRange>> mappedRanges = new HashMap<>();
    buildRangesFromInput(lines, mappedRanges);

    List<RegexMatch> matches =
        PatternUtils.getAllMatchesFromInput(lines.get(0).get(0), PatternUtils.INT_PATTERN);
    long min = Long.MAX_VALUE;
    for (int i = 0; i < matches.size(); i += 2) {
      long seedStart = Long.parseLong(matches.get(i).getValue());
      long length = Long.parseLong(matches.get(i + 1).getValue());
      Range seedRange = Range.of(seedStart, seedStart + length);
      List<Range> locations = getLocationRangesFromSeedRanges(seedRange, mappedRanges);
      for (Range range : locations) {
        min = Math.min(min, range.start);
      }
    }
    return String.valueOf(min);
  }

  public void buildRangesFromInput(
      List<List<String>> lines, Map<String, List<MappedRange>> ranges) {
    for (int i = 1; i < lines.size(); i++) {
      List<String> group = lines.get(i);
      List<MappedRange> range = new ArrayList<>();
      for (int j = 1; j < group.size(); j++) {
        String[] values = group.get(j).split(" ");
        long destStart = Long.parseLong(values[0]);
        long srcStart = Long.parseLong(values[1]);
        long length = Long.parseLong(values[2]);
        range.add(new MappedRange(new Range(srcStart, srcStart + length), destStart));
      }
      String elementString = group.get(0);
      String element = elementString.substring(0, elementString.indexOf("-"));
      ranges.put(element, range);
    }
  }

  public long getLocationFromSeed(long seed, Map<String, List<MappedRange>> ranges) {
    long value = seed;
    for (String element : elements) {
      value = getValueFromRangeList(value, ranges.get(element));
    }
    return value;
  }

  public long getValueFromRangeList(long value, List<MappedRange> ranges) {
    for (MappedRange range : ranges) {
      if (range.contains(value)) {
        return range.getDestinationValue(value);
      }
    }
    return value;
  }

  public List<Range> getLocationRangesFromSeedRanges(
      Range range, Map<String, List<MappedRange>> mappedRanges) {
    List<Range> ranges = List.of(range);
    for (String element : elements) {
      List<Range> outputs = new ArrayList<>();
      for (Range current : ranges) {
        outputs.addAll(getOutputRangesFromInputRange(current, mappedRanges.get(element)));
      }

      ranges = outputs;
    }
    return ranges;
  }

  public List<Range> getOutputRangesFromInputRange(Range range, List<MappedRange> ranges) {
    List<Range> outputRanges = new ArrayList<>();
    boolean finished = false;
    while (!finished) {
      boolean matched = false;
      for (MappedRange mappedRange : ranges) {
        if (mappedRange.sourceRange.contains(range)) {
          outputRanges.add(
              new Range(
                  mappedRange.getDestinationValue(range.start),
                  mappedRange.getDestinationValue(range.end)));
          matched = true;
          finished = true;
        } else if (mappedRange.sourceRange.contains(range.start)) {
          outputRanges.add(
              new Range(
                  mappedRange.getDestinationValue(range.start),
                  mappedRange.getDestinationValue(mappedRange.sourceRange.end)));
          range = Range.of(mappedRange.sourceRange.end, range.end);
          matched = true;
        } else if (mappedRange.sourceRange.contains(range.end - 1)) {
          outputRanges.add(
              new Range(
                  mappedRange.getDestinationValue(mappedRange.sourceRange.start),
                  mappedRange.getDestinationValue(range.end)));
          range = Range.of(range.start, mappedRange.sourceRange.start);
          matched = true;
        }
      }
      if (!matched) {
        outputRanges.add(range);
        finished = true;
      }
    }
    return outputRanges;
  }

  @Data
  @AllArgsConstructor
  static class MappedRange implements Comparable<MappedRange> {
    Range sourceRange;
    long destStart;

    public boolean contains(long val) {
      return sourceRange.contains(val);
    }

    public long getDestinationValue(long value) {
      return value - sourceRange.start + destStart;
    }

    @Override
    public int compareTo(MappedRange o) {
      return this.sourceRange.compareTo(o.sourceRange);
    }

    @Override
    public String toString() {
      return String.format(
          "Source: %s, Dest: [%d, %d)", sourceRange, destStart, destStart + sourceRange.length());
    }
  }
}
