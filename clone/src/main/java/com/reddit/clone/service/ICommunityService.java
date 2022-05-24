package com.reddit.clone.service;

import com.reddit.clone.model.Community;

import java.util.List;

public interface ICommunityService
{
    Community saveCommunity(Community community);

    List<Community> findAll();
}
