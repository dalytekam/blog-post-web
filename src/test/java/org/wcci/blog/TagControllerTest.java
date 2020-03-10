package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.TagController;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


public class TagControllerTest {

    private TagController underTest;
    private Model model;
    private BlogServiceImpl mockStorage;
    private Tag testTag;


    @BeforeEach
    void setUp() {
        mockStorage = mock(BlogServiceImpl.class);
        underTest = new TagController(mockStorage);
        model = mock(Model.class);
        testTag = new Tag("test tag");
        when(mockStorage.findTagById(2L)).thenReturn(testTag);
    }

        @Test
        public void getSingleTagReturnsTagPostsCollection() {
            String result = underTest.getSingleTag(2L, model);
            assertThat(result).isEqualTo("tagsPostCollection");
        }
    @Test
    public void getSingleTagInteractsWithDependenciesCorrectly() {

        underTest.getSingleTag(2L, model);
        verify(mockStorage).findTagById(2l);
        verify(model).addAttribute("singleTag", testTag);
    }

    @Test
    public void getSingleTagMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/tag/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("tagsPostCollection"))
                .andExpect(model().attributeExists("singleTag"))
                .andExpect(model().attribute("singleTag", testTag));
    }

    }
