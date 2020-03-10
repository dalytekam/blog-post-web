package org.wcci.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.entities.Author;
import org.wcci.blog.service.BlogServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



    @ExtendWith(SpringExtension.class)
    @WebMvcTest
    public class WebLayerTest {

        @MockBean
        BlogServiceImpl mockStorage;
        @Autowired
        private MockMvc mockMvc;


        @Test
        public void authorShouldBeOKAndReturnTheAuthorViewWithAuthorModelAttribute() throws Exception {
            mockMvc.perform(get("/authors"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("authors"))
                    .andExpect(model().attributeExists("authors"));
        }

        @Test
        public void shouldReceiveOKFromSingleAuthorEndpoint() throws Exception {
            Author testAuthor = new Author("test author");
            when(mockStorage.findAuthorById(1L)).thenReturn(testAuthor);
            mockMvc.perform(get("/author/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("authorPostCollection"))
                    .andExpect(model().attributeExists("singleAuthor"));
        }

        @Test
        public void shouldBeAbleToCreateNewAuthor() throws Exception {
            mockMvc.perform(post("/save-author")
                    .param("authorName", "test author"))
                    .andExpect(status().is2xxSuccessful());
            verify(mockStorage).saveAuthor("test author");
        }



}
