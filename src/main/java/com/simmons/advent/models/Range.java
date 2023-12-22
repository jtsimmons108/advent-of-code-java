package com.simmons.advent.models;

import lombok.Data;

@Data
public class Range implements Comparable<Range> {
  public final long start;
  public final long end;

  public static Range of(long start, long end) {
    return new Range(start, end);
  }

  public static Range of(long end) {
    return new Range(end);
  }

  public Range(long start, long end) {
    this.start = start;
    this.end = end;
  }

  public Range(long end) {
    this(0, end);
  }

  public boolean contains(long val) {
    return start <= val && val < end;
  }

  public boolean contains(Range other) {
    return this.start <= other.start && other.end <= this.end;
  }

  public boolean intersects(Range other) {
    return other.contains(this.start) || this.contains(other.start);
  }

  public long length() {
    return end - start;
  }

  @Override
  public int compareTo(Range o) {
    if (this.start == o.start) {
      return Long.compare(this.end, o.end);
    }
    return Long.compare(this.start, o.start);
  }

  @Override
  public String toString() {
    return String.format("[%d, %d)", start, end);
  }
}
