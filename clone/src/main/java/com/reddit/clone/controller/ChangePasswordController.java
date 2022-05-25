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
@RequestMapping(value="api/change_password",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class ChangePasswordController
{
    @Autowired
    private IUserService userService;

   @PostMapping
    public ResponseEntity<User> ResetUserPassword(@RequestBody MultiValueMap<String, String> formData) {
       User temp = userService.findByUsername(formData.getFirst("username"));
       if(temp == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       if(!temp.getPassword().equals(formData.getFirst("password"))) {
           return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
       }
       temp.setPassword(formData.getFirst("newpassword"));
       userService.saveUser(temp);
       return new ResponseEntity<>(temp, HttpStatus.OK);
    }
}