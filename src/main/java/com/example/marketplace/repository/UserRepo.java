package com.example.marketplace.repository;

import com.example.marketplace.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
