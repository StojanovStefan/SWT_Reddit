package com.reddit.clone.repository;

import com.reddit.clone.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface IReactionRepository extends JpaRepository<Reaction, Integer>, JpaSpecificationExecutor<Reaction> {
    @Query(value = "SELECT * FROM Reaction RE JOIN User US ON US.user_id = RE.user_id WHERE US.username = ?1", nativeQuery = true)
    Collection<Reaction> findByUsername(String username);

    Collection<Reaction> findByPostId(Integer id);

    Collection<Reaction> findByUserId(Integer id);
}