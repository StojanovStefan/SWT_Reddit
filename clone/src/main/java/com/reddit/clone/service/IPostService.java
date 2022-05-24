package com.reddit.clone.service;

import com.reddit.clone.model.Post;

import java.util.List;

public interface IPostService
{
    Post savePost(Post post);

    List<Post> findAll();
}
