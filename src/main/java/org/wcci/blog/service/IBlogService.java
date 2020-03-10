package org.wcci.blog.service;

import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;

import java.util.List;


public interface IBlogService {


    public List<Author> listOfAllAuthors();
    public List<Tag> listOfAllTags();
    public List<Post> listOfAllPosts();
    public List<Category> listOfAllCategories();

    public void saveAuthor(String authName);
    public void saveTag(String tagName);
    public void savePost(Post post);
    public void saveCategory(String catName);


    public void addTagToAPost(Tag tag,Post post);
    public void addCategoryToAPost(Category category, Post post);

    public Post findPostById(Long id);
    public Tag findTagById(Long id);
    public Author findAuthorById(Long id);
    public Category findCategoryById(Long id);
    public List<Author> findAuthorByName(String authName);
    public List<Category> findCategoryByName(String catName);
    public List<Tag> findTagByName(String tagName);

}
