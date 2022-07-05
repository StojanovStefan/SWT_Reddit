package com.reddit.clone.controller;

import com.reddit.clone.model.Reaction;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.IReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/reaction")
@CrossOrigin(origins = {"*"})
public class ReactionController {
    @Autowired
    private IReactionRepository reactionRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<Reaction> deleteReaction(@PathVariable("id") Integer id, @RequestAttribute(value = "currentuser") User currentUser) {
        Optional<Reaction> reaction = reactionRepository.findById(id);
        if (reaction.isPresent()) {
            if (reaction.get().getUser().getId() == currentUser.getId()) {
                reactionRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<Reaction>> getAllReaction() {
        return new ResponseEntity<>(reactionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reaction> getReactionById(@PathVariable Integer id) {
        if (reactionRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reactionRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Collection<Reaction>> getReactionByPostId(@PathVariable("id") Integer id) {
        if (reactionRepository.findByPostId(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reactionRepository.findByPostId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Collection<Reaction>> getReactionByUserID(@PathVariable("id") Integer id) {
        if (reactionRepository.findByUserId(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reactionRepository.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Collection<Reaction>> getReactionByUsername(@PathVariable("username") String username) {
        if (reactionRepository.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reactionRepository.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reaction> insertReaction(@RequestBody Reaction reaction) {
        if (reaction.getId() == null || reaction.getId() == 0) {
            reaction.setId(null);
            Reaction temp = reactionRepository.save(reaction);
            return new ResponseEntity<>(temp, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reaction> updateReaction(@PathVariable("id") Integer id, @RequestAttribute(value = "currentuser") User currentUser, @RequestBody Reaction reaction) {
        Optional<Reaction> temp = reactionRepository.findById(id);
        if (temp.isPresent()) {
            if (Objects.equals(temp.get().getUser().getId(), currentUser.getId())) {
                reaction.setId(id);
                reactionRepository.save(reaction);
                return new ResponseEntity<>(reaction, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
