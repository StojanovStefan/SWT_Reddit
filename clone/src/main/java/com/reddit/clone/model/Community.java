package com.reddit.clone.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "community")
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "community_id", nullable = false)
    private Integer communityId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_data")
    private String creationData;

    @Column(name = "is_suspended")
    private Boolean suspended;

    @Column(name = "suspended_reason")
    private String suspendedReason;

}
