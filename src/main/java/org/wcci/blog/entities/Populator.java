package org.wcci.blog.entities;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import org.wcci.blog.dao.AuthorRepository;
import org.wcci.blog.dao.CategoryRepository;
import org.wcci.blog.dao.PostRepository;
import org.wcci.blog.dao.TagRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class Populator implements CommandLineRunner {
    private PostRepository postRepository;
    private AuthorRepository authorRepository;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;

    public Populator(PostRepository postRepository, AuthorRepository authorRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        // Create categories
        Category food = new Category("food");
        Category drink = new Category("drink");
        Category car = new Category("car");
        //save categories
        categoryRepository.save(food);
        categoryRepository.save(drink);
        categoryRepository.save(car);

        // Create tags
        Tag hot = new Tag("hot");
        Tag trendy = new Tag("trendy");
        Tag mom = new Tag("mom");
        Tag summer = new Tag("summer");
        // Save tags
        tagRepository.save(hot);
        tagRepository.save(trendy);
        tagRepository.save(mom);
        tagRepository.save(summer);

        //Create authors
        Author daly = new Author("Daly");
        Author richy = new Author("Richy");
        Author tekam = new Author("Tekam");
        Author kuate = new Author("Kuate");

        //Save authors
        authorRepository.save(richy);
        authorRepository.save(daly);
        authorRepository.save(tekam);
        authorRepository.save(kuate);

        // Create post
        Post post1 = new Post("My first Post", "is simply dummy text of the printing and  beatae culpa velit odit nobis eius aut doloribus ex mollitia. Ab\n" +
                "            non accusantium aliquam id eum labore, ipsum deleniti quas? Lorem\n" +
                "            ipsum, dolor sit amet consectetur adipisicing elit. Sequi, Lorem\n" +
                "            ipsum dolor sit, amet consectetur adipisicing elit. Labore tempore\n" +
                "            iure minima earum aperiam reprehenderit libero nobis iste itaque\n" +
                "            sed consectetur aspernatur deserunt a aliquam fugiat, magnam\n" +
                "            dignissimos distinctio nisi nesciunt. Sit ducimus quas omnis\n" +
                "            alias. Quia mollitia doloribus impedit amet, sequi eos dolorum\n" +
                "            architecto expedita fugiat illo maxime nesciunt rem neque est\n" +
                "            velit alias voluptatem? Soluta officia neque a. ", daly, LocalDateTime.now());
        Post post2 = new Post("My second Post", "is simply dummy ext of the printing a exercitationem quo\n" +
                "                                    qui\n" +
                "                                    quidem aperiam eos autem voluptates dolor eveniet! Quaerat perferendis beatae\n" +
                "                                    consequuntur aspernatur nulla veritatis labore illo vero", richy,LocalDateTime.now());
        Post post3 = new Post("My third Post", "is simply dummy text of the printing and t exercitationem quo\n" +
                "                                    qui\n" +
                "                                    quidem aperiam eos autem voluptates dolor eveniet! Quaerat perferendis beatae\n" +
                "                                    consequuntur aspernatur nulla veritatis labore illo vero", tekam,LocalDateTime.now());

        //Add categories to post
        post1.getPostCategories().add(food);
        post1.getPostCategories().add(drink);
        post2.getPostCategories().add(drink);
        post2.getPostCategories().add(car);
        post3.getPostCategories().add(car);
        post1.getPostCategories().add(car);

        // Add tags to post
        post1.getPostTags().add(hot);
        post1.getPostTags().add(mom);
        post1.getPostTags().add(trendy);
        post2.getPostTags().add(hot);
        post2.getPostTags().add(summer);
        post2.getPostTags().add(mom);

        // save post
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        // Date time formatter
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        postRepository.findAll().forEach(post -> {
            System.out.println(post.getPostId());
            System.out.println(post.getPostTitle());
            System.out.println(post.getPostAuthor().getAuthorName());
            post.getPostCategories().forEach(c->{
                System.out.println(c.getCategoryName());
            });
            post.getPostTags().forEach(t->{
                System.out.println(t.getTagName());
            });

                System.out.println(post.getPostDate().format(myDateFormat));
        });

          tagRepository.findAll().forEach(t->{
              System.out.println(t.getTagName()+" : "+t.getTagPosts().size());
              t.getTagPosts().forEach(p->{
                  System.out.println(p.getPostTitle());
              });
          });

    }


}
