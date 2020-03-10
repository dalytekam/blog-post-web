package org.wcci.blog;

import org.junit.jupiter.api.Test;
import org.wcci.blog.controller.HomeController;
import org.wcci.blog.controller.PostController;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeControllerTest {


    private HomeController underTest;





    @Test
    public void indexRequestShouldReturnsIndexView() {
        underTest = new HomeController();
        String result = underTest.home();
        assertThat(result).isEqualTo("index");
    }
}
