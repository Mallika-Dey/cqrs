package com.example.ProductService.command.api.controller;

import com.example.ProductService.command.api.commands.CreateProductCommand;
import com.example.ProductService.command.api.commands.DeleteProductCommand;
import com.example.ProductService.command.api.commands.UpdateProductCommand;
import com.example.ProductService.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addProduct(@RequestBody ProductRestModel productRestModel) {
        CreateProductCommand createProductCommand =
                CreateProductCommand
                        .builder()
                        .aggregateId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();

        String response = commandGateway.sendAndWait(createProductCommand);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer productId,
                                                @RequestBody ProductRestModel productRestModel) {
        UpdateProductCommand updateProductCommand =
                UpdateProductCommand
                        .builder()
                        .aggregateId(UUID.randomUUID().toString())
                        .productId(productId)
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();

        String response = commandGateway.sendAndWait(updateProductCommand);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        String response = commandGateway.sendAndWait(
                new DeleteProductCommand(UUID.randomUUID().toString(), productId));
        //String response = commandGateway.sendAndWait(productId);
        return ResponseEntity.ok(response);
    }
}
