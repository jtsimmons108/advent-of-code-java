package com.simmons.advent.utils;

import com.simmons.advent.models.RegexMatch;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

  public static final Pattern INT_PATTERN = Pattern.compile("\\d+");

  public static Pattern getOverlappingPattern(String pattern) {
    return Pattern.compile(String.format("(?=(%s)).*(%s)", pattern, pattern));
  }

  public static List<RegexMatch> getAllMatchesFromInput(String input, Pattern pattern) {
    List<RegexMatch> matches = new ArrayList<>();
    Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      matches.add(RegexMatch.of(matcher.group(), matcher.start(), matcher.end()));
    }
    return matches;
  }
}
