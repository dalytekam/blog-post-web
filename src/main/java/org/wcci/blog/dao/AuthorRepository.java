package org.wcci.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wcci.blog.entities.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {


    List<Author> findByAuthorName(String authorName);

}
