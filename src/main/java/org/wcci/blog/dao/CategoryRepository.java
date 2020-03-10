package org.wcci.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByCategoryName(String categoryName);
}
