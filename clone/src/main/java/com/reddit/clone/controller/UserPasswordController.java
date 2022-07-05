package com.reddit.clone.controller;

import com.reddit.clone.model.User;
import com.reddit.clone.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "api/change_password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
@CrossOrigin(origins = {"*"})
public class UserPasswordController {
    @Autowired
    private IUserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> ResetUserPassword(@RequestBody MultiValueMap<String, String> formData) {
        Optional<User> temp = userRepository.findByUsername(formData.getFirst("username"));
        if (temp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!temp.get().getPassword().equals(formData.getFirst("password"))) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        temp.get().setPassword(formData.getFirst("newpassword"));
        userRepository.save(temp.get());
        return new ResponseEntity<>(temp.get(), HttpStatus.OK);
    }
}