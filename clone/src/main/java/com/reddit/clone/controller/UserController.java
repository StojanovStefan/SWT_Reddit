package com.reddit.clone.controller;

import com.reddit.clone.model.User;
import com.reddit.clone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController
{
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        return  new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

}
