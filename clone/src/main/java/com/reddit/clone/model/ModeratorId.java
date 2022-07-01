package com.reddit.clone.model;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
public class ModeratorId implements Serializable {
    private static final long serialVersionUID = 5896125692256265148L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "community_id", nullable = false)
    private Integer communityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ModeratorId entity = (ModeratorId) o;
        return Objects.equals(this.communityId, entity.communityId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(communityId, userId);
    }

}