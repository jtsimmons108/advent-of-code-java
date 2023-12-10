package com.simmons.advent.error;

public class NaughtyException extends RuntimeException {

  public NaughtyException(String message, Throwable cause) {
    super(message, cause);
  }

  public NaughtyException(String message) {
    super(message);
  }
}
