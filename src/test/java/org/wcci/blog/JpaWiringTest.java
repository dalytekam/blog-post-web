package org.wcci.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.dao.AuthorRepository;
import org.wcci.blog.dao.CategoryRepository;
import org.wcci.blog.dao.PostRepository;
import org.wcci.blog.dao.TagRepository;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



    @DataJpaTest
    public class JpaWiringTest {
        @Autowired
        private AuthorRepository authorRepository;
        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private PostRepository postRepository;
        @Autowired
        private TagRepository tagRepository;
        @Autowired
        private TestEntityManager entityManager;


        @Test
        public void postShouldHaveAuthor() {
            Author testAuthor1 = new Author("testAuthor1");
            Post testPost1 = new Post("testTitle1", "testBody1", testAuthor1, LocalDateTime.now());
            authorRepository.save(testAuthor1);
            postRepository.save(testPost1);
            entityManager.flush();
            entityManager.clear();

            Post retrievedPost = postRepository.findById(testPost1.getPostId()).get();
            assertThat(retrievedPost.getPostAuthor().getAuthorName().contains("testAuthor1"));

        }
          @Test
        public void postShouldBeAbleToHaveZeroOrMultipleTags(){
              Author testAuthor2 = new Author("testAuthor2");
              Post testPost2 = new Post("testTitle2", "testBody2", testAuthor2, LocalDateTime.now());
              Post testPost3 = new Post("testTitle3", "testBody3", testAuthor2, LocalDateTime.now());

              Tag testTag1 = new Tag("testTag1");
              Tag testTag2 = new Tag("testTag2");
              Tag testTag3 = new Tag("testTag3");

              testPost2.getPostTags().add(testTag1);
              testPost2.getPostTags().add(testTag2);
              tagRepository.save(testTag1);
              tagRepository.save(testTag2);
              tagRepository.save(testTag3);
              postRepository.save(testPost2);
              authorRepository.save(testAuthor2);
              postRepository.save(testPost3);

              entityManager.flush();
              entityManager.clear();
              Post retrievedPost2 = postRepository.findById(testPost2.getPostId()).get();
              assertThat(retrievedPost2.getPostTags().contains(testTag1));
              assertThat(retrievedPost2.getPostTags().contains(testTag2));
              assertThat(retrievedPost2.getPostTags()).doesNotContain(testTag3);


              Post retrievedPost3=postRepository.findById(testPost3.getPostId()).get();
              assertThat(retrievedPost3.getPostTags()).isEmpty();
          }

        @Test
        public void postShouldBeAbleToHaveZeroOrMultipleCategories() {
            Category testCategory1 = new Category("testCategory1");
            Category testCategory2 = new Category("testCategory2");
            categoryRepository.save(testCategory1);
            categoryRepository.save(testCategory2);

            Author testAuthor4 = new Author("testAuthor4");;
            Post testPost4 = new Post("testTitle4", "testBody4", testAuthor4, LocalDateTime.now());
            authorRepository.save(testAuthor4);
            testPost4.getPostCategories().add(testCategory1);
            testPost4.getPostCategories().add(testCategory2);

            postRepository.save(testPost4);

            entityManager.flush();
            entityManager.clear();

            Post retrievedPost4 = postRepository.findById(testPost4.getPostId()).get();
            assertThat(retrievedPost4.getPostCategories().contains(testCategory1));
            assertThat(retrievedPost4.getPostCategories().contains(testCategory2));


            // Category should have a post

            Category retrievedCategory =categoryRepository.findById(testCategory1.getCategoryId()).get();
            assertThat(retrievedCategory.getCategoryPosts().contains(testPost4));

        }


   }





