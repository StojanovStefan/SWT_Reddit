package com.reddit.clone.repository;

import com.reddit.clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface IPostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    Collection<Post> findByCommunityId(Integer id);

    Collection<Post> findByUserId(Integer id);

    @Query(value = "SELECT * FROM Post PO JOIN User US ON US.user_id = PO.user_id WHERE US.username = ?1", nativeQuery = true)
    Collection<Post> findByUsername(String username);
}