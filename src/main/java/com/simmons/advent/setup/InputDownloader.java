package com.simmons.advent.setup;

import com.simmons.advent.error.NaughtyException;
import java.io.File;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.apache.commons.io.FileUtils;

public class InputDownloader {

  public static final String AOC_COOKIE = "AOC_COOKIE";

  public static void downloadInputToFile(int year, int day) {
    ZonedDateTime current = ZonedDateTime.now(ZoneId.of("America/New_York"));
    String cookie = System.getenv(AOC_COOKIE);
    if (year > current.getYear() || (year == current.getYear() && day > current.getDayOfMonth())) {
      throw new NaughtyException(String.format("Cannot download day in future: Day %d %d", day, year));
    }
    File file =
        Paths.get(
                InputFileCreator.BASE_RESOURCE_PATH,
                String.valueOf(year),
                String.format("day%02d.txt", day))
            .toFile();
    if (file.length() > 0) {
      throw new NaughtyException("Skipping download. File already downloaded");
    }

    try {

      CookieHandler.setDefault(new CookieManager());

      HttpCookie sessionCookie = new HttpCookie("session", cookie);
      sessionCookie.setPath("/");
      sessionCookie.setVersion(0);

      ((CookieManager) CookieHandler.getDefault())
          .getCookieStore()
          .add(new URI("https://adventofcode.com"), sessionCookie);

      HttpClient client =
          HttpClient.newBuilder()
              .cookieHandler(CookieHandler.getDefault())
              .connectTimeout(Duration.ofSeconds(10))
              .build();

      HttpRequest req =
          HttpRequest.newBuilder()
              .uri(URI.create(String.format("https://adventofcode.com/%d/day/%d/input", year, day)))
              .GET()
              .build();

      String input = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
      FileUtils.writeStringToFile(file, input, Charset.defaultCharset());
    } catch (Exception exception) {
      throw new NaughtyException("Unable to download file", exception);
    }
  }
}
