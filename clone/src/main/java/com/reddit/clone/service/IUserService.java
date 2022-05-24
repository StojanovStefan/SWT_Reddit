package com.reddit.clone.service;

import com.reddit.clone.model.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    List<User> findAll();
}
