package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.error.NaughtyException;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day07_2023 extends AbstractDay {

  public static final List<Character> ORDER =
      List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');
  public static final List<Character> JOKER_ORDER =
      List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');

  public static final char JOKER = 'J';

  public Day07_2023() {
    super(2023, 7);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Hand> hands = buildHandListFromInput(lines, false);
    long score = getScoresFromHands(hands);
    return String.valueOf(score);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    List<Hand> hands = buildHandListFromInput(lines, true);
    long score = getScoresFromHands(hands);
    return String.valueOf(score);
  }

  private List<Hand> buildHandListFromInput(List<String> lines, boolean hasJokers) {
    List<Hand> hands = new ArrayList<>();
    for (String line : lines) {
      int space = line.indexOf(" ");
      String hand = line.substring(0, space);
      String bid = line.substring(space + 1);
      hands.add(new Hand(hand, Integer.parseInt(bid), hasJokers));
    }
    Collections.sort(hands);
    return hands;
  }

  private long getScoresFromHands(List<Hand> hands) {
    long total = 0;
    for (int i = 0; i < hands.size(); i++) {
      total += hands.get(i).bid * (hands.size() - i);
    }
    return total;
  }

  @Data
  class Hand implements Comparable<Hand> {
    final String hand;
    final int bid;
    final boolean hasJokers;
    final List<Card> cards;

    public Hand(String hand, int bid, boolean hasJokers) {
      this.hand = hand;
      this.bid = bid;
      this.hasJokers = hasJokers;
      cards = new ArrayList<>();
      buildCardListFromHand();
    }

    private void buildCardListFromHand() {
      Map<Character, Integer> counts = new HashMap<>();
      for (char c : hand.toCharArray()) {
        counts.put(c, counts.getOrDefault(c, 0) + 1);
      }
      for (Map.Entry<Character, Integer> card : counts.entrySet()) {
        cards.add(new Card(card.getKey(), card.getValue()));
      }
      Collections.sort(cards);
      if (hasJokers && counts.containsKey(JOKER)) {
        int index = 0;
        while (index < cards.size() && cards.get(index).c == 'J') {
          index++;
        }
        if (index < cards.size()) {
          cards.get(index).count += counts.get('J');
        }

        if (counts.size() > 1) { // Don't remove the Joker if it's the only card
          for (int i = cards.size() - 1; i >= 0; i--) {
            if (cards.get(i).c == 'J') {
              cards.remove(i);
            }
          }
        }
      }
    }

    @Override
    public int compareTo(Hand o) {
      int index = 0;
      while (index < this.cards.size()) {
        if (this.cards.get(index).compareTo(o.cards.get(index)) != 0) {
          return this.cards.get(index).compareTo(o.cards.get(index));
        }
        index++;
      }

      for (int i = 0; i < hand.length(); i++) {
        if (hand.charAt(i) != o.hand.charAt(i)) {
          List<Character> currentOrder = hasJokers ? JOKER_ORDER : ORDER;
          return currentOrder.indexOf(hand.charAt(i)) - currentOrder.indexOf(o.hand.charAt(i));
        }
      }
      throw new NaughtyException("This shouldn't happen");
    }
  }

  @Data
  @AllArgsConstructor
  class Card implements Comparable<Card> {
    final char c;
    int count;

    @Override
    public int compareTo(Card o) {
      return o.count - this.count;
    }
  }
}
