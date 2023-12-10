package com.simmons.advent.days.model;

import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.setup.InputDownloader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;

public abstract class AbstractDay implements Day {
  protected String input;

  public AbstractDay(int year, int day) {
    String resourcePath = String.format("inputs/%d/day%02d.txt", year, day);
    downloadInputIfNeeded(year, day, resourcePath);
    try {
      input =
          IOUtils.resourceToString(
              resourcePath, Charset.defaultCharset(), AbstractDay.class.getClassLoader());
    } catch (IOException e) {
      throw new NaughtyException("Unable to load resource", e);
    }
  }

  private void downloadInputIfNeeded(int year, int day, String resourcePath) {
    File file = Paths.get("src", "main", "resources", resourcePath).toFile();
    if (file.length() == 0) {
      System.out.println(resourcePath + " is empty. Trying to download");
      try {
        InputDownloader.downloadInputToFile(year, day);
      } catch (NaughtyException e) {
        System.out.println(e.getMessage());
      }
    } else {
      System.out.println("Input file already downloaded");
    }
  }

  public String getInput() {
    return input;
  }
}
