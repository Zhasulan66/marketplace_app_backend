package com.example.marketplace.controller;

import com.example.marketplace.entity.Category;
import com.example.marketplace.entity.Product;
import com.example.marketplace.entity.User;
import com.example.marketplace.exception.CategoryNotFoundException;
import com.example.marketplace.exception.UserNotFoundException;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.updateProduct(id, updatedProduct);
        return new ResponseEntity<>(existingProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + categoryId));

        List<Product> products = productService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-title")
    public ResponseEntity<List<Product>> getProductsByTitle(@RequestParam String title) {
        List<Product> products = productService.getProductsByTitle(title);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<Product>> getProductsByUser(@RequestParam Long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Product> products = productService.getProductsByUser(user);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}


//JSON for add product
    /*{
        "title": "Product Name",
            "description": "Product Description",
            "price": 19.99,
            "category": {
        "id": 1
    },
        "user": {
        "id": 123
    }
    }*/
