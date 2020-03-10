package org.wcci.blog.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    @NotNull
    private String authorName;
    @OneToMany(mappedBy = "postAuthor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Post> authorPosts = new HashSet<>();



    public Author(String authorName) {

        this.authorName = authorName;

    }

    public Long getAuthorId() {

        return authorId;
    }

    public String getAuthorName() {

        return authorName;
    }

    public Collection<Post> getAuthorPosts() {
        return authorPosts;
    }
    public Author() {
    }


}
