package com.simmons.advent.days.base;

public class TestArgument {

  private String input;
  private String output;

  public TestArgument(String input, String output) {
    this.input = input;
    this.output = output;
  }

  public static TestArgument of(String input, String output) {
    return new TestArgument(input, output);
  }

  public String getInput() {
    return input;
  }

  public String getOutput() {
    return output;
  }
}
