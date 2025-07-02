package com.nexus.ecommerce.service;

import com.nexus.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product updateProduct(Long productId, Product productDetails);
    void deleteProduct(Long productId);
}
