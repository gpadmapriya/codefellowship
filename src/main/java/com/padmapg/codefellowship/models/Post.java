package com.padmapg.codefellowship.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "text")
    String body;
    Timestamp createdAt;

    @ManyToOne
    ApplicationUser owner;

    public Post(){}
    public Post(String body, Timestamp createdAt, ApplicationUser owner){
        this.body = body;
        this.createdAt = createdAt;
        this.owner = owner;
    }
    public Long getId() {
        return id;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getBody() {
        return body;
    }
}
