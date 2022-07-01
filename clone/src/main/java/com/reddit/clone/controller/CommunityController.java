package com.reddit.clone.controller;

import com.reddit.clone.model.Community;
import com.reddit.clone.model.Moderator;
import com.reddit.clone.model.ModeratorId;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.ICommunityRepository;
import com.reddit.clone.repository.IModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/community")
public class CommunityController {
    @Autowired
    private ICommunityRepository communityRepository;

    @Autowired
    private IModeratorRepository moderatorRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<Community> deleteCommunity(@PathVariable("id") Integer id) {
        if (communityRepository.existsById(id)) {
            communityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<Community>> getAllCommunity() {
        return new ResponseEntity<>(communityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Integer id) {
        if (communityRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(communityRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<Community>> getCommunityByName(@PathVariable("name") String name) {
        if (communityRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(communityRepository.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Community> insertCommunity(@RequestBody Community community) {
        if (community.getId() == null || community.getId() == 0) {
            community.setId(null);
            Community temp = communityRepository.save(community);
            return new ResponseEntity<>(temp, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable("id") Integer id, @RequestAttribute(value = "currentuser") User currentUser, @RequestBody Community community) {
        Optional<Moderator> mod = moderatorRepository.findById(new ModeratorId(currentUser.getId(), id));
        if (mod.isPresent()) {
            community.setId(id);
            communityRepository.save(community);
            return new ResponseEntity<>(community, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
