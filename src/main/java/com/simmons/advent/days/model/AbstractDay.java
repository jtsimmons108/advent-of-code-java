package com.simmons.advent.days.model;

import com.simmons.advent.error.NaughtyException;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

public abstract class AbstractDay implements Day {
  protected String input;

  public AbstractDay(int year, int day) {
    String resourcePath = String.format("inputs/%d/day%02d.txt", year, day);
    try {
      input =
          IOUtils.resourceToString(
              resourcePath, Charset.defaultCharset(), AbstractDay.class.getClassLoader());
    } catch (IOException e) {
      throw new NaughtyException("Unable to load resource", e);
    }
  }

  public String getInput() {
    return input;
  }
}
