package com.simmons.advent.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CircularLinkedList<T> {

  public static final String INDEX_OUT_OF_BOUNDS_FORMAT = "Unable to access index %d with size %d";

  private ListNode<T> head;
  private ListNode<T> tail;
  private int size;

  public CircularLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  public CircularLinkedList(Collection<T> collection) {
    for (T value : collection) {
      add(value);
    }
  }

  public void add(T value) {
    ListNode<T> node = new ListNode<>(value);
    if (head == null) {
      head = node;
      tail = node;
      head.next = node;
    } else {
      tail.next = node;
      tail = node;
      tail.next = head;
    }
    size++;
  }

  public void insert(int index, T value) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_FORMAT, index, size));
    }
    if (index == size) {
      add(value);
      return;
    }
    ListNode<T> node = new ListNode<>(value);

    if (index == 0) {
      node.next = head;
      head = node;
      tail.next = head;
    } else {
      ListNode<T> prev = getNodeAtIndex(index - 1);
      node.next = prev.next;
      prev.next = node;
    }
    size++;
  }

  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_FORMAT, index, size));
    }
    return getNodeAtIndex(index).value;
  }

  public T remove(T value) {
    int index = indexOf(value);
    if (index > -1) {
      removeAtIndex(index);
      return value;
    }
    return null;
  }

  public T removeAtIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_FORMAT, index, size));
    }
    if (head == tail) {
      T value = head.value;
      head = null;
      tail = null;
      return value;
    }

    T value = null;
    if (index == 0) {
      value = head.value;
      head = head.next;
      tail.next = head;
    } else {
      ListNode<T> prev = getNodeAtIndex(index - 1);
      value = prev.next.value;
      prev.next = prev.next.next;
      if (index == size - 1) {
        tail = prev;
      }
    }
    size--;
    return value;
  }

  public int indexOf(T value) {
    if (head == null) {
      return -1;
    }
    int index = 0;
    ListNode<T> current = head;
    do {
      if (current.value.equals(value)) {
        return index;
      }
      index++;
      current = current.next;
    } while (current != head);
    return -1;
  }

  private ListNode<T> getNodeAtIndex(int index) {
    ListNode<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public List<T> subList(int startIndex, int endIndex) {
    if (startIndex < 0) {
      throw new IndexOutOfBoundsException(
          String.format(INDEX_OUT_OF_BOUNDS_FORMAT, startIndex, size));
    }
    if (endIndex > size) {
      throw new IndexOutOfBoundsException(
          String.format(INDEX_OUT_OF_BOUNDS_FORMAT, endIndex, size));
    }
    ListNode<T> current = getNodeAtIndex(startIndex);
    List<T> list = new ArrayList<>(endIndex - startIndex);
    for (int i = startIndex; i < endIndex; i++) {
      list.add(current.value);
      current = current.next;
    }
    return list;
  }

  public List<T> toList() {
    return subList(0, size);
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append('[');
    if (head != null) {
      ListNode<T> current = head;
      do {
        builder.append(current.value);
        if (current.next != head) {
          builder.append(", ");
        }
        current = current.next;
      } while (current != head);
    }
    builder.append(']');
    return builder.toString();
  }
}
