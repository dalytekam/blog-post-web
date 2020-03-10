package org.wcci.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcci.blog.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
