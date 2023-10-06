package com.example.marketplace.service;

import com.example.marketplace.entity.Category;
import com.example.marketplace.entity.Product;
import com.example.marketplace.entity.User;
import com.example.marketplace.exception.CategoryNotFoundException;
import com.example.marketplace.exception.ProductNotFoundException;
import com.example.marketplace.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepo.findByCategory(category);
    }

    public List<Product> getProductsByUser(User user) {
        return productRepo.findByUser(user);
    }

    public List<Product> getProductsByTitle(String title) {
        return productRepo.findByTitleContaining(title);
    }


    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        updatedProduct.setProductID(id); // Set the ID of the updated product
        return productRepo.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }


}
