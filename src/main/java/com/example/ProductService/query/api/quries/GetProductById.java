package com.example.ProductService.query.api.quries;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetProductById {
    private final Integer productId;
}
