package com.example.ProductService.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class DeleteProductCommand {
    @TargetAggregateIdentifier
    private String aggregateId;

    private Integer deleteProductId;
}
