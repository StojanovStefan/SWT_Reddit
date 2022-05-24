package com.reddit.clone.service;

import com.reddit.clone.model.User;
import com.reddit.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

}
