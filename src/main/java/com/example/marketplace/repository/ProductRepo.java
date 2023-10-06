package com.example.marketplace.repository;

import com.example.marketplace.entity.Category;
import com.example.marketplace.entity.Product;
import com.example.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByUser(User user);

    List<Product> findByTitleContaining(String title);
}
