package com.nexus.ecommerce.service;

import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElseThrow(() -> new RuntimeException("Product not found with id: "+productId));
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, Product productDetails){
        Product existingProduct = getProductById(productId);
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId){
        Product productToDelete = getProductById(productId);
        productRepository.delete(productToDelete);
    }
}
