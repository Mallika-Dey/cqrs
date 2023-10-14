package com.example.ProductService.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdatedEvent {
    private String aggregateId;
    private Integer productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
