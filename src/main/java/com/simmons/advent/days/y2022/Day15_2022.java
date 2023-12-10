package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.models.Point;
import com.simmons.advent.models.Range;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.Data;

public class Day15_2022 extends AbstractDay {

  public static final Pattern LINE_PATTERN =
      Pattern.compile(
          "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

  public final int gridLimit;
  public static final int X_MULITPLIER = 4_000_000;

  public Day15_2022(int gridLimit){
    super(2022, 15);
    this.gridLimit = gridLimit;
  }
  public Day15_2022() {
    super(2022, 15);
    this.gridLimit = 4_000_000;
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Sensor> sensors = getSensorsFromInput(lines);
    Set<Point> beacons = sensors.stream().map(sensor -> sensor.beacon).collect(Collectors.toSet());

    final long TARGET = gridLimit / 2;

    List<Range> ranges = getAllBlockedRanges(sensors, TARGET);
    List<Range> merged = mergeRanges(ranges);

    long total = 0;
    for (Range range : merged) {
      total += range.length();
    }
    for (Point location : beacons) {
      if (location.y == TARGET) {
        if (merged.stream().anyMatch(range -> range.contains(location.x))) {
          total--;
        }
      }
    }
    return String.valueOf(total);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Sensor> sensors = getSensorsFromInput(lines);
    Set<Point> beacons = sensors.stream().map(sensor -> sensor.beacon).collect(Collectors.toSet());

    long ans = 0;

    for (int target = 0; target <= gridLimit; target++) {
      List<Range> ranges = getAllBlockedRanges(sensors, target);
      List<Range> merged = mergeRanges(ranges);
      for (int i = 0; i < merged.size(); i++) {
        Range current = merged.get(i);
        if (current.start < 0 || current.end > gridLimit) {
          merged.set(i, Range.of(Math.max(0, current.start), Math.min(current.end, gridLimit)));
        }
      }
      long total = 0;
      for (Range range : merged) {
        total += range.length();
      }
      boolean containsBeacon = false;
      for (Point location : beacons) {
        if (location.y == target) {
          if (merged.stream().anyMatch(range -> range.contains(location.x))) {
            total--;
            containsBeacon = true;
          }
        }
      }
      if (!containsBeacon && total == gridLimit - 1) {
        long x = merged.get(0).end;
        long y = target;
        ans = x * X_MULITPLIER + y;
      }
    }
    return String.valueOf(ans);
  }

  private Range getBlockedXRangeFromSensor(Sensor sensor, long row) {
    long distRemaining = sensor.distance - Math.abs(sensor.location.y - row);
    if (distRemaining < 0) {
      return null;
    }
    return Range.of(sensor.location.x - distRemaining, sensor.location.x + distRemaining + 1);
  }

  private List<Range> getAllBlockedRanges(List<Sensor> sensors, long row){
    List<Range> ranges = new ArrayList<>();
    for (Sensor sensor : sensors) {
      Range range = getBlockedXRangeFromSensor(sensor, row);
      if (range != null) {
        ranges.add(range);
      }
    }
    return ranges;
  }
  private List<Sensor> getSensorsFromInput(List<String> lines){
    List<Sensor> sensors = new ArrayList<>();
    for (String line : lines) {
      Matcher matcher = LINE_PATTERN.matcher(line);
      if (matcher.matches()) {
        Point location =
                new Point(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)));
        Point beacon =
                new Point(Long.parseLong(matcher.group(3)), Long.parseLong(matcher.group(4)));
        sensors.add(new Sensor(location, beacon));
      }
    }
    return sensors;
  }

  private List<Range> mergeRanges(List<Range> ranges){
    ranges.sort(Comparator.comparingLong(r -> r.start));
    List<Range> merged = new ArrayList<>();
    merged.add(ranges.get(0));
    for (int i = 1; i < ranges.size(); i++) {
      Range current = ranges.get(i);
      int end = merged.size() - 1;
      Range last = merged.get(end);
      if (current.start <= last.end) {
        if (current.end > last.end) {
          merged.set(end, Range.of(last.start, current.end));
        }
      } else {
        merged.add(current);
      }
    }
    return merged;
  }

  @Data
  class Sensor {
    final Point location;
    final Point beacon;
    final long distance;

    public Sensor(Point location, Point beacon) {
      this.location = location;
      this.beacon = beacon;
      this.distance = location.manhattanDistance(beacon);
    }
  }
}
