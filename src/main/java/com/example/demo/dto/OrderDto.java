package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.model.Order;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderDto {

  @JsonProperty("order_id")
  private Integer orderId;

  @JsonProperty("order_status")
  private Integer orderStatus;

  @JsonProperty("last_update_time")
  private LocalDateTime lastUpdateTime;

  @JsonProperty("product_id")
  private Integer productId;

  @JsonProperty("progress_remaining")
  private Integer progressRemaining;

  public static OrderDto fromEntity(Order order) {
    return new OrderDto(
        order.orderId(),
        order.orderStatus(),
        order.lastUpdateTime(),
        order.product().productId(),
        order.progressRemaining());
  }

  public OrderDto() {}

  public OrderDto(
      Integer orderId,
      Integer orderStatus,
      LocalDateTime lastUpdateTime,
      Integer productId,
      Integer progressRemaining) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.lastUpdateTime = lastUpdateTime;
    this.productId = productId;
    this.progressRemaining = progressRemaining;
  }

  public Integer orderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer orderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public LocalDateTime lastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public Integer productId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer progressRemaining() {
    return progressRemaining;
  }

  public void setProgressRemaining(Integer progressRemaining) {
    this.progressRemaining = progressRemaining;
  }
}
