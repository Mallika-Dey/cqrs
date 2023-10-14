package com.example.ProductService.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeletedEvent {
    private String aggregateId;
    private Integer deleteProductId;
}
