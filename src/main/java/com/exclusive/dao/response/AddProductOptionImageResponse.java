package com.exclusive.dao.response;

import com.exclusive.exclusive.entity.ProductOptionsImages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductOptionImageResponse {

  private ProductOptionsImages productOptionsImages;
  private String message;

}
