package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.AuthorController;
import org.wcci.blog.controller.PostController;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Post;
import org.wcci.blog.service.BlogServiceImpl;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class PostControllerTest {

    private PostController underTest;
    private Model model;
    private BlogServiceImpl mockStorage;
    private Post testPost;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        mockStorage = mock(BlogServiceImpl.class);
        underTest = new PostController(mockStorage);
        model = mock(Model.class);
        testAuthor= new Author("test author");
        testPost = new Post("postTitle","postBody",testAuthor, LocalDateTime.now());
        when(mockStorage.findPostById(2L)).thenReturn(testPost);
        when(mockStorage.findAuthorById(1L)).thenReturn(testAuthor);
    }

    @Test
    public void getSinglePostReturnsSinglePostView() {
        String result = underTest.getSinglePost(2L, model);
        assertThat(result).isEqualTo("single-post");
    }
    @Test
    public void getSinglePostInteractsWithDependenciesCorrectly() {

        underTest.getSinglePost(2L, model);
        verify(mockStorage).findPostById(2L);
        verify(model).addAttribute("singlePost", testPost);
    }

    @Test
    public void getSinglePostMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/post/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("single-post"))
                .andExpect(model().attributeExists("singlePost"))
                .andExpect(model().attribute("singlePost", testPost));
    }





}
