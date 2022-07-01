package com.reddit.clone.controller;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private IPostRepository postRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id, @RequestAttribute(value = "currentuser") User currentUser) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            if (post.get().getUser().getId() == currentUser.getId()) {
                postRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<Post>> getAllPost() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        if (postRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<Collection<Post>> getReactionByPostId(@PathVariable("id") Integer id) {
        if (postRepository.findByCommunityId(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findByCommunityId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Collection<Post>> getReactionByUserID(@PathVariable("id") Integer id) {
        if (postRepository.findByUserId(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Collection<Post>> getReactionByUsername(@PathVariable("username") String username) {
        if (postRepository.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> insertPost(@RequestBody Post post) {
        if (post.getId() == null || post.getId() == 0) {
            post.setId(null);
            Post temp = postRepository.saveAndFlush(post);
            return new ResponseEntity<>(temp, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id, @RequestAttribute(value = "currentuser") User currentUser, @RequestBody Post post) {
        Optional<Post> temp = postRepository.findById(id);
        if (temp.isPresent()) {
            if (Objects.equals(temp.get().getUser().getId(), currentUser.getId())) {
                post.setId(id);
                postRepository.save(post);
                return new ResponseEntity<>(post, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}