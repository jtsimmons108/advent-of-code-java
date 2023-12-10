package com.simmons.advent.data;

public class ListNode<T> {
  T value;
  ListNode<T> next;

  public ListNode(T value) {
    this.value = value;
  }

  public String toString() {
    return String.valueOf(value);
  }
}
