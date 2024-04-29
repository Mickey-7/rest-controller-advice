package com.example.validationspringboot.controller;

import com.example.validationspringboot.entity.User;
import com.example.validationspringboot.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers(){
        return  userRepository.findAll();
    }

    @PostMapping("/users")
    ResponseEntity<String> addUser(@Valid @RequestBody User user){
        return ResponseEntity.ok("User is valid");
    }

    @PostMapping("/deserialize")
    ResponseEntity<String> addUserWrongFieldName(@RequestBody User user){
        //try sending on postman
        /*
        * {
            "groups":"dfad",
            "email":"fadsa"
        }
        */
        //the name field is group from the request
        //which will be taken care of CustomDeserializer
        //on util
        return ResponseEntity.ok("User is valid");
    }

    @PostMapping("/users/header")
    ResponseEntity<String> addUserWithHeaderValidation(@RequestHeader("header-id") String headerId, @Valid @RequestBody User user){
        return ResponseEntity.ok("User is valid");
    }







}
