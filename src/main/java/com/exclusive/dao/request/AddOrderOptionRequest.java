package com.exclusive.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderOptionRequest {
  private Long orderId;
  private Long productId;
  private Integer quantity;
}
