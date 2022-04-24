package ru.job4j.dream.controller;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenAddPost() {
        Post input = new Post(1, "New post");
        City city = new City();
        input.setCity(city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        when(cityService.findById(1)).thenReturn(city);
        String page = postController.addPost(input, 1);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        List<City> cities = Arrays.asList(new City(), new City());
        User user = new User();
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        when(session.getAttribute("user")).thenReturn(user);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formAddPost(model, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void whenUpdatePost() {
        Post update = new Post(1, "Update post");
        City city = new City();
        update.setCity(city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        when(cityService.findById(1)).thenReturn(city);
        String page = postController.updatePost(update, 1);
        verify(postService).update(update);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormUpdatePost() {
        List<City> cities = Arrays.asList(new City(), new City());
        Post post = new Post(1, "Current post");
        User user = new User();
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        when(session.getAttribute("user")).thenReturn(user);
        when(postService.findById(1)).thenReturn(post);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formUpdatePost(model, 1, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("post", post);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updatePost"));
    }

}