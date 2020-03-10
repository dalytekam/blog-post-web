package org.wcci.blog.service;

import org.springframework.stereotype.Service;
import org.wcci.blog.dao.AuthorRepository;
import org.wcci.blog.dao.CategoryRepository;
import org.wcci.blog.dao.PostRepository;
import org.wcci.blog.dao.TagRepository;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
   private PostRepository postRepository;
   private TagRepository tagRepository;
   private CategoryRepository categoryRepository;
   private AuthorRepository authorRepository;

    public BlogServiceImpl(PostRepository postRepository, TagRepository tagRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> listOfAllAuthors() {
        List<Author> allAuthors = authorRepository.findAll();
        return allAuthors;
    }

    @Override
    public List<Tag> listOfAllTags() {
        List<Tag> allTags = tagRepository.findAll();
        return allTags;
    }

    @Override
    public List<Post> listOfAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        allPosts=postRepository.findAll();
        return allPosts;
    }

    @Override
    public List<Category> listOfAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories;
    }

    @Override
    public void saveAuthor(String authName) {
     List<Author> allAuthors = authorRepository.findByAuthorName(authName);
     if(allAuthors.size() ==0){
         Author authorToSave= new Author(authName);
     authorRepository.save(authorToSave);}



    }

    @Override
    public void saveTag(String tagName) {
        List<Tag> allTags = tagRepository.findByTagName(tagName);
        if(allTags.size() ==0){
        Tag tagToSave= new Tag(tagName);
        tagRepository.save(tagToSave);}
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void saveCategory(String catName) {
        // Check the case to implement
        List<Category> allCategories = categoryRepository.findByCategoryName(catName);
        if(allCategories.size() ==0){
     Category categoryToSave = new Category(catName);
     categoryRepository.save(categoryToSave);}
    }

    @Override
    public void addTagToAPost(Tag tag, Post post) {
       post.getPostTags().add(tag);
    }

    @Override
    public void addCategoryToAPost(Category category, Post post) {
       post.getPostCategories().add(category);
    }

    @Override
    public Post findPostById(Long id) {

        return postRepository.findById(id).get();
    }

    @Override
    public Tag findTagById(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Author> findAuthorByName(String authName) {
        return authorRepository.findByAuthorName(authName);
    }

    @Override
    public List<Category> findCategoryByName(String catName) {
        return categoryRepository.findByCategoryName(catName);
    }


    @Override
    public List<Tag> findTagByName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }
}
