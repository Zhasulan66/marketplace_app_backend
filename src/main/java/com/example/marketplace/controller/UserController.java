package com.example.marketplace.controller;

import com.example.marketplace.exception.UserAlreadyExistException;
import com.example.marketplace.exception.UserNotFoundException;
import com.example.marketplace.entity.User;
import com.example.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    @GetMapping
    public ResponseEntity getAllUser() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }
}
