package com.example.marketplace.service;

import com.example.marketplace.entity.Category;
import com.example.marketplace.exception.UserAlreadyExistException;
import com.example.marketplace.exception.UserNotFoundException;
import com.example.marketplace.entity.User;
import com.example.marketplace.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User registration(User user) throws UserAlreadyExistException {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("User Already Exist");
        }
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public User getUser(String email) throws UserNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }

    public List<User> getUsers() {
        return (List<User>) userRepo.findAll();
    }

}