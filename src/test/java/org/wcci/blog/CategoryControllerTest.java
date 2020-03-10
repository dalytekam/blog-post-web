package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.CategoryController;
import org.wcci.blog.controller.TagController;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.service.BlogServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class CategoryControllerTest {

    private CategoryController underTest;
    private Model model;
    private BlogServiceImpl mockStorage;
    private Category testCategory;


    @BeforeEach
    void setUp() {
        mockStorage = mock(BlogServiceImpl.class);
        underTest = new CategoryController(mockStorage);
        model = mock(Model.class);
        testCategory = new Category("test category");
        when(mockStorage.findCategoryById(4L)).thenReturn(testCategory);
    }

    @Test
    public void getSingleCategoryReturnsCategoryPostsCollection() {
        String result = underTest.getSingleCategory(4L, model);
        assertThat(result).isEqualTo("categoriesPostCollection");
    }
    @Test
    public void getSingleCategoryInteractsWithDependenciesCorrectly() {

        underTest.getSingleCategory(4L, model);
        verify(mockStorage).findCategoryById(4L);
        verify(model).addAttribute("singleCategory", testCategory);
    }

    @Test
    public void getSingleCategoryMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/category/4"))
                .andExpect(status().isOk())
                .andExpect(view().name("categoriesPostCollection"))
                .andExpect(model().attributeExists("singleCategory"))
                .andExpect(model().attribute("singleCategory", testCategory));
    }





}
