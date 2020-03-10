package org.wcci.blog.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @NotNull
    private String tagName;
    @ManyToMany(mappedBy = "postTags")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Post> tagPosts =new HashSet<>();

    public Tag() {
    }
    public Tag(String tagName) {

        this.tagName= tagName;



    }



    public Long getTagId() {
        return tagId;
    }

    public String getTagName()
    {
        return tagName;
    }

    public Collection<Post> getTagPosts() {
        return tagPosts;
    }
}
