package org.wcci.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class PostController {
   private BlogServiceImpl blogServiceImpl;

    public PostController(BlogServiceImpl blogServiceImpl) {
        this.blogServiceImpl = blogServiceImpl;
    }

// Get all the posts
    @GetMapping("/all-posts")
    public String getPosts(Model model){
    List<Post> posts= blogServiceImpl.listOfAllPosts();
    model.addAttribute("posts",posts);
        return "all-posts";
    }


// Get single Post
    @GetMapping("/post/{id}")
    public String getSinglePost(@PathVariable Long id, Model model){
        Post retrievedPost = blogServiceImpl.findPostById(id);
        model.addAttribute("singlePost",retrievedPost);
        return "single-post";
    }

// Add a new Post
    @GetMapping("/add-post")
    public String addPost(Model model){
        model.addAttribute("authors",blogServiceImpl.listOfAllAuthors());
        model.addAttribute("categories", blogServiceImpl.listOfAllCategories());
        model.addAttribute("tags", blogServiceImpl.listOfAllTags());
        return "add-post";
    }

   @PostMapping("/save-post")
   public String savePost(@RequestParam String postTitle, @RequestParam String postBody, @RequestParam String authorId, @RequestParam(defaultValue = "") String[] categories, @RequestParam(defaultValue = "") String[] tags, Model model) {

       Long idofAuthor = Long.parseLong(authorId);
       Author authorOfPost = blogServiceImpl.findAuthorById(idofAuthor);
       Post postNewlyCreated = new Post(postTitle, postBody, authorOfPost, LocalDateTime.now());

           if(categories.length>0){
       for (String categoryId : categories) {
           Long idOfCategory = Long.parseLong(categoryId);
           Category categoryToFind = blogServiceImpl.findCategoryById(idOfCategory);
           blogServiceImpl.addCategoryToAPost(categoryToFind, postNewlyCreated);
       }}
       if(tags.length>0){
           for (String tagId : tags) {
               Long idOfTag = Long.parseLong(tagId);
               Tag tagToFind = blogServiceImpl.findTagById(idOfTag);
               blogServiceImpl.addTagToAPost(tagToFind, postNewlyCreated);
           }}
           blogServiceImpl.savePost(postNewlyCreated);

           return "succes";


       }



   }


