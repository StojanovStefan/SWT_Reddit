package com.reddit.clone.repository;

import com.reddit.clone.model.Moderator;
import com.reddit.clone.model.ModeratorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IModeratorRepository extends JpaRepository<Moderator, ModeratorId>, JpaSpecificationExecutor<Moderator> {
}
