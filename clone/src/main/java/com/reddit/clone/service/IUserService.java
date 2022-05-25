package com.reddit.clone.service;

import com.reddit.clone.model.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByPassword(String password);

    List<User> findAll();

}
