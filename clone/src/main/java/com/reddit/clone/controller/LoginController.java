package com.reddit.clone.controller;

import com.reddit.clone.model.User;
import com.reddit.clone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class LoginController
{
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<User> UserByUsername(@RequestBody MultiValueMap<String, String> formData) {
        return new ResponseEntity<>(userService.findByUsername(formData.getFirst("username")), HttpStatus.CREATED);
    }

}
