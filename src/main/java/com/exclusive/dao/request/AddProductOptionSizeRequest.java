package com.exclusive.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductOptionSizeRequest {
  private String productOptionsSize;
  private Long productOptionId;
}
