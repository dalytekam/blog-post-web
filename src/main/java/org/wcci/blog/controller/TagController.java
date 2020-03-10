package org.wcci.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;

import java.util.List;

@Controller
public class TagController {
    private BlogServiceImpl blogServiceImpl;

    public TagController(BlogServiceImpl blogServiceImpl) {
        this.blogServiceImpl = blogServiceImpl;
    }

    @GetMapping("/tags")
    public String getTags(Model model){
            List<Tag> tags= blogServiceImpl.listOfAllTags();
            model.addAttribute("tags",tags);
        return "tags";



    }


    @GetMapping("/add-tag")
    public String addTag(){
        return "add-tag";
    }

    @PostMapping("/save-tag")
    public String saveAuthor(@RequestParam String tagName){
        blogServiceImpl.saveTag(tagName);
        return "succes";
    }

    @GetMapping("/tag/{id}")
    public String getSingleTag(@PathVariable Long id, Model model){
        Tag retrievedTag = blogServiceImpl.findTagById(id);
        model.addAttribute("singleTag",retrievedTag);

        return "tagsPostCollection";
    }
}
