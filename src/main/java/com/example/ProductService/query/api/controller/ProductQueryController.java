package com.example.ProductService.query.api.controller;

import com.example.ProductService.command.api.data.Product;
import com.example.ProductService.command.api.model.ProductRestModel;
import com.example.ProductService.query.api.quries.GetProductById;
import com.example.ProductService.query.api.quries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public List<ProductRestModel> getAllProducts(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        List<ProductRestModel> productRestModels =
        queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();
        return productRestModels;
    }

    @GetMapping("/get/{productId}")
    public Product getById(@PathVariable Integer productId){
        GetProductById getProductById = new GetProductById(productId);

        return queryGateway.query(getProductById,
                ResponseTypes.instanceOf(Product.class))
                .join();
    }
}
