package com.reddit.clone.service;

import com.reddit.clone.model.Post;
import com.reddit.clone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService
{
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post)
    {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll()
    {
        return postRepository.findAll();
    }

}
