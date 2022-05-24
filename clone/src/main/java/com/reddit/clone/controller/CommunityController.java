package com.reddit.clone.controller;

import com.reddit.clone.model.Community;
import com.reddit.clone.service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/community")
public class CommunityController
{
    @Autowired
    private ICommunityService communityService;

    @PostMapping
    public ResponseEntity<?> saveCommunity(@RequestBody Community community)
    {
        return new ResponseEntity<>(communityService.saveCommunity(community), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<?> updateCommunity(@RequestBody Community community)
    {
        return  new ResponseEntity<>(communityService.saveCommunity(community), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCommunities()
    {
        return ResponseEntity.ok(communityService.findAll());
    }

}
