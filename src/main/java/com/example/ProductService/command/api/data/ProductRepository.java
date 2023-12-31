package com.example.ProductService.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public void deleteByProductId(Integer productId);

    public Optional<Product> findByProductId(Integer productId);
}
