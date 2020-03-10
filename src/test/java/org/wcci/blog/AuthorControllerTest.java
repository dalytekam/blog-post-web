package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.AuthorController;
import org.wcci.blog.controller.TagController;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class AuthorControllerTest {


    private AuthorController underTest;
    private Model model;
    private BlogServiceImpl mockStorage;
    private Author testAuthor;


    @BeforeEach
    void setUp() {
        mockStorage = mock(BlogServiceImpl.class);
        underTest = new AuthorController(mockStorage);
        model = mock(Model.class);
        testAuthor = new Author("test author");
        when(mockStorage.findAuthorById(12L)).thenReturn(testAuthor);
    }

    @Test
    public void getSingleAuthorReturnsAuthorPostsCollection() {
        String result = underTest.getSingleAuthor(12L, model);
        assertThat(result).isEqualTo("authorPostCollection");
    }
    @Test
    public void getSingleAuthorInteractsWithDependenciesCorrectly() {

        underTest.getSingleAuthor(12L, model);
        verify(mockStorage).findAuthorById(12L);
        verify(model).addAttribute("singleAuthor", testAuthor);
    }

    @Test
    public void getSingleAuthorMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/author/12"))
                .andExpect(status().isOk())
                .andExpect(view().name("authorPostCollection"))
                .andExpect(model().attributeExists("singleAuthor"))
                .andExpect(model().attribute("singleAuthor", testAuthor));
    }

}
