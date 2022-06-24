package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController
{
    @Autowired
    private IPostService postService;

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Post post)
    {
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(@RequestBody Post post)
    {
        return  new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts()
    {
        return ResponseEntity.ok(postService.findAll());
    }

}