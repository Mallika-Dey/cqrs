package com.example.ProductService.command.api.aggregate;

import com.example.ProductService.command.api.commands.DeleteProductCommand;
import com.example.ProductService.command.api.events.ProductDeletedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class DeleteProductAggregate {
    @AggregateIdentifier
    private String aggregateId;
    private Integer deleteProductId;

    @CommandHandler
    public DeleteProductAggregate(DeleteProductCommand deleteProductCommand) {
        AggregateLifecycle.apply(ProductDeletedEvent
                .builder()
                .aggregateId(deleteProductCommand.getAggregateId())
                .deleteProductId(deleteProductCommand.getDeleteProductId())
                .build());
    }

    @EventSourcingHandler
    public void deleteProduct(ProductDeletedEvent productDeletedEvent) {
        this.aggregateId = productDeletedEvent.getAggregateId();
        this.deleteProductId = productDeletedEvent.getDeleteProductId();
    }
}
