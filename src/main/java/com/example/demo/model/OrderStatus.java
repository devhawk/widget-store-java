package com.example.demo.model;

public enum OrderStatus {
  PENDING(0),
  DISPATCHED(1),
  PAID(2),
  CANCELLED(-1);

  private final int value;

  OrderStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static OrderStatus fromValue(int value) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.value == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown order status: " + value);
  }
}
