package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductDto {

  @JsonProperty("product_id")
  private Integer productId;

  @JsonProperty("product")
  private String product;

  @JsonProperty("description")
  private String description;

  @JsonProperty("inventory")
  private Integer inventory;

  @JsonProperty("price")
  private BigDecimal price;

  public static ProductDto fromEntity(Product product) {
    return new ProductDto(
        product.productId(),
        product.product(),
        product.description(),
        product.inventory(),
        product.price());
  }

  public ProductDto() {}

  public ProductDto(
      Integer productId, String product, String description, Integer inventory, BigDecimal price) {
    this.productId = productId;
    this.product = product;
    this.description = description;
    this.inventory = inventory;
    this.price = price;
  }

  public Integer productId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String product() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String description() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer inventory() {
    return inventory;
  }

  public void setInventory(Integer inventory) {
    this.inventory = inventory;
  }

  public BigDecimal price() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
