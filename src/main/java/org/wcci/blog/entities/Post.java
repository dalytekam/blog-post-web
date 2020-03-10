package org.wcci.blog.entities;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
   // @NotNull
    private String postTitle;
   // @NotNull
    @Column(columnDefinition="TEXT")
    private String postBody;
    @ManyToOne
    private Author postAuthor;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Category> postCategories = new HashSet<>();
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Tag> postTags =new HashSet<>();

    private LocalDateTime postDate = LocalDateTime.now();


    public Post(String postTitle, String postBody, Author postAuthor, LocalDateTime postDate) {

        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postAuthor = postAuthor;
        this.postDate = postDate;

    }

    public Long getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public Author getPostAuthor() {
        return postAuthor;
    }

    public Collection<Category> getPostCategories() {
        return postCategories;
    }

    public Collection<Tag> getPostTags() {
        return postTags;
    }

    public LocalDateTime getPostDate() {

        return postDate;
    }
    public Post() {
    }
}
