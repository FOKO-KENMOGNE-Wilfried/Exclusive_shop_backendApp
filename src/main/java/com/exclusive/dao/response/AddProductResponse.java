package com.exclusive.dao.response;

import com.exclusive.exclusive.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductResponse {

  private Product product;
  private String message;

}
