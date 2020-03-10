package org.wcci.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;

import java.util.List;

@Controller
public class CategoryController {

    private BlogServiceImpl blogServiceImpl;

    public CategoryController(BlogServiceImpl blogServiceImpl) {
        this.blogServiceImpl = blogServiceImpl;
    }


    @GetMapping("/categories")
    public String getCategories(Model model){
            List<Category> categories = blogServiceImpl.listOfAllCategories();
            model.addAttribute("categories", categories);
        return "categories";
    }



    @GetMapping("/add-category")

    public String addCategory(){
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(@RequestParam String categoryName){
        blogServiceImpl.saveCategory(categoryName);
        return "succes";
    }

    @GetMapping("/category/{id}")
    public String getSingleCategory(@PathVariable Long id, Model model){
        Category retrievedCategory = blogServiceImpl.findCategoryById(id);
        model.addAttribute("singleCategory",retrievedCategory);

        return "categoriesPostCollection";
    }

}
