package com.reddit.clone.repository;

import com.reddit.clone.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommunityRepository extends JpaRepository<Community, Integer>, JpaSpecificationExecutor<Community>
{

}