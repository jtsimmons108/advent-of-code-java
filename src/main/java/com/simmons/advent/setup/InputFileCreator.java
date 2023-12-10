package com.simmons.advent.setup;

import com.simmons.advent.error.NaughtyException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class InputFileCreator {

  public static final String BASE_PACKAGE_PATH = "src/main/java/com/simmons/advent/days";
  public static final String BASE_PACKAGE_NAME = "com.simmons.advent.days";
  public static final String BASE_RESOURCE_PATH = "src/main/resources/inputs";

  public static final int DAYS = 25;

  public static void main(String[] args) {
    for (int year = 2015; year < 2024; year++) {
      createInputFiles(year);
    }
  }

  public static void createJavaFiles(int year) {
    File directory = ensureDirectory(BASE_PACKAGE_PATH, "y%d", year);
    for (int day = 1; day <= DAYS; day++) {
      File dayFile = ensureFile(directory, String.format("Day%%02d_%d.java", year), day);
      if (dayFile.length() == 0) {
        StringBuilder fileBuilder = new StringBuilder();
        fileBuilder.append(String.format("package %s.y%d;\n\n", BASE_PACKAGE_NAME, year));
        fileBuilder.append("import com.simmons.advent.days.model.AbstractDay;\n\n");
        fileBuilder.append(
            String.format("public class Day%02d_%d extends AbstractDay {\n\n", day, year));
        fileBuilder.append(String.format("  public Day%02d_%d() {\n", day, year));
        fileBuilder.append(String.format("    super(%d, %d);\n", year, day));
        fileBuilder.append("  }\n\n");
        fileBuilder.append("  public String solvePartOne(String input) { return null; }\n\n");
        fileBuilder.append("  public String solvePartTwo(String input) { return null; }\n\n");
        fileBuilder.append("}");
        try {
          FileUtils.writeStringToFile(dayFile, fileBuilder.toString(), Charset.defaultCharset());
        } catch (IOException e) {
          throw new NaughtyException("Unable to write data to file", e);
        }
      }
    }
  }

  public static void createInputFiles(int year) {
    File baseDir = new File(BASE_RESOURCE_PATH);
    if (!baseDir.exists()) {
      baseDir.mkdir();
    }
    File directory = ensureDirectory(BASE_RESOURCE_PATH, "%d", year);
    for (int day = 1; day <= DAYS; day++) {
      ensureFile(directory, "day%02d.txt", day);
    }
  }

  private static File ensureDirectory(String base, String format, int year) {
    File directory = Paths.get(base, String.format(format, year)).toFile();
    if (!directory.exists()) {
      directory.mkdir();
    }
    return directory;
  }

  private static File ensureFile(File directory, String format, int day) {
    File file = Paths.get(directory.getPath(), String.format(format, day)).toFile();
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new NaughtyException("Unable to make file", e);
      }
    }
    return file;
  }
}
