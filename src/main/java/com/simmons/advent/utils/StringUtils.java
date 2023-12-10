package com.simmons.advent.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simmons.advent.error.NaughtyException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  public static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
  public static final Pattern INT_PATTERN = Pattern.compile("\\d+");
  public static final Pattern INT_PATTERN_WITH_NEGATIVES = Pattern.compile("-?\\d+");

  public static String getRepeatedString(String value, int repetitions) {
    StringBuilder builder = new StringBuilder(value.length() * repetitions);
    for (int i = 0; i < repetitions; i++) {
      builder.append(value);
    }
    return builder.toString();
  }

  public static Set<Character> getCommonCharacters(String... strings) {
    if (strings == null) {
      return Collections.EMPTY_SET;
    }
    if (strings.length == 1) {
      return getCharSet(strings[0]);
    }
    Set<Character> start = getCharSet(strings[0]);
    return Arrays.stream(strings)
        .map(StringUtils::getCharSet)
        .reduce(
            start,
            (first, second) -> {
              first.retainAll(second);
              return first;
            });
  }

  public static Set<Character> getCharSet(String string) {
    char[] chars = string.toCharArray();
    return IntStream.range(0, chars.length).mapToObj(i -> chars[i]).collect(Collectors.toSet());
  }

  public static <T> T getStringAsType(String data, Class<? extends T> clazz) {
    try {
      return OBJECT_MAPPER.readValue(data, clazz);
    } catch (JsonProcessingException e) {
      throw new NaughtyException("Unable to convert value", e);
    }
  }

  public static <T> T getStringAsType(String data, TypeReference<? extends T> typeReference) {
    try {
      return OBJECT_MAPPER.readValue(data, typeReference);
    } catch (JsonProcessingException e) {
      throw new NaughtyException("Unable to convert value", e);
    }
  }

  public static List<Long> extractIntsFromString(String input, boolean includeNegatives) {
    Pattern pattern = includeNegatives ? INT_PATTERN_WITH_NEGATIVES : INT_PATTERN;
    return extractNumberPatternsFromString(input, pattern);
  }

  public static List<Long> extractDigitsFromString(String input) {
    return extractNumberPatternsFromString(input, DIGIT_PATTERN);
  }

  private static List<Long> extractNumberPatternsFromString(String input, Pattern pattern) {
    List<Long> ints = new ArrayList<>();
    Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      ints.add(Long.parseLong(matcher.group()));
    }
    return ints;
  }
}
