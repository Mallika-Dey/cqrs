package com.example.ProductService.command.api.events;

import com.example.ProductService.command.api.data.Product;
import com.example.ProductService.command.api.data.ProductRepository;
import com.example.ProductService.command.api.exception.CustomException;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductEventHandler {

    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product =
                Product
                        .builder()
                        .name(event.getName())
                        .price(event.getPrice())
                        .quantity(event.getQuantity())
                        .build();

        productRepository.save(product);

        // Intentional create exception
        //throw new Exception("Exception occurred");
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) throws Exception {
        Product product = productRepository.findByProductId(event.getProductId()).orElseThrow(
                () -> new CustomException(new Date(), "product not exist", HttpStatus.NOT_FOUND)
        );
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductDeletedEvent productDeletedEvent) throws Exception {
        productRepository.deleteByProductId(productDeletedEvent.getDeleteProductId());
    }
}
