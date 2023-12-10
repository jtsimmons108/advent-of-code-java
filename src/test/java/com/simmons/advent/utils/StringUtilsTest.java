package com.simmons.advent.utils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

  @Test
  void testGetCharSet() {
    Assertions.assertEquals(Set.of('a', 'b', 'c'), StringUtils.getCharSet("aaabbc"));
  }

  @Test
  void testGetCommonCharacters() {
    Assertions.assertEquals(
        Set.of('c', 'd', 'e'), StringUtils.getCommonCharacters("abcde", "cde", "cdefg"));
  }

  @Test
  void testGetCommonCharactersNoCommon() {
    Assertions.assertEquals(
        Collections.EMPTY_SET, StringUtils.getCommonCharacters("ab", "cd", "ef"));
  }

  @Test
  void testGetCommonCharactersOneString() {
    Assertions.assertEquals(Set.of('a', 'b'), StringUtils.getCommonCharacters("ab"));
  }

  @Test
  void testGetCommonCharactersNull() {
    Assertions.assertEquals(Collections.EMPTY_SET, StringUtils.getCommonCharacters(null));
  }

  @Test
  void testGetMappedObjectWithClass() {
    String input = "[1,1,3,1,1]";
    List<Object> expected = List.of(1, 1, 3, 1, 1);
    Assertions.assertEquals(expected, StringUtils.getStringAsType(input, List.class));
  }

  @Test
  void testGetMappedObjectWithClassNested() {
    String input = "[[1],[2,3,4]]";
    List<Object> expected = List.of(List.of(1), List.of(2, 3, 4));
    Assertions.assertEquals(expected, StringUtils.getStringAsType(input, List.class));
  }

  @Test
  void testExtractInts() {
    Assertions.assertEquals(List.of(1L, 2L), StringUtils.extractIntsFromString("1abc2", false));
    Assertions.assertEquals(
        List.of(1L, 2L, 3L, 4L, 5L), StringUtils.extractIntsFromString("a1b2c3d4e5f", false));
  }
}
