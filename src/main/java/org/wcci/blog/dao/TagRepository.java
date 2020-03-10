package org.wcci.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    List<Tag> findByTagName(String tagName);
}
