package com.simmons.advent.models;

public class RegexMatch {

  private String value;
  private int start, end;

  public RegexMatch(String value, int start, int end) {
    this.value = value;
    this.start = start;
    this.end = end;
  }

  public static RegexMatch of(String value, int start, int end) {
    return new RegexMatch(value, start, end);
  }

  public String getValue() {
    return value;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public String toString() {
    return String.format("[%d,%d]%s", start, end, value);
  }
}
