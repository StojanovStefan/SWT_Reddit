package com.reddit.clone.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "community")
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
