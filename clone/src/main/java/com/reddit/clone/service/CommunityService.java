package com.reddit.clone.service;

import com.reddit.clone.model.Community;
import com.reddit.clone.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService implements ICommunityService
{
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public Community saveCommunity(Community community)
    {
        return communityRepository.save(community);
    }

    @Override
    public List<Community> findAll()
    {
        return communityRepository.findAll();
    }
}
