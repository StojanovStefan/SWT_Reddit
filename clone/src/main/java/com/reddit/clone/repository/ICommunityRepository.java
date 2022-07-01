package com.reddit.clone.repository;

import com.reddit.clone.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface ICommunityRepository extends JpaRepository<Community, Integer>, JpaSpecificationExecutor<Community> {

    Collection<Community> findByNameContainingIgnoreCase(String name);
}