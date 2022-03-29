package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    private static BasicDataSource pool;
    private static PostDBStore store;

    @BeforeClass
    public static void init() {
        pool = new Main().loadPool();
        store = new PostDBStore(pool);
    }

    @After
    public void deleteTable() throws SQLException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from post")) {
            statement.execute();
        }
    }

    @Test
    public void whenCreatePost() {
        Post post = new Post(0, "Java Job", true, new City(1, "Москва"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenFindByIdPost() {
        Post post1 = new Post(0, "Java Job1", true, new City(1, "Москва"));
        Post post2 = new Post(0, "Java Job2", true, new City(1, "Москва"));
        Post post3 = new Post(0, "Java Job3", true, new City(1, "Москва"));
        store.add(post1);
        store.add(post2);
        store.add(post3);
        Post postInDb = store.findById(post2.getId());
        assertThat(postInDb.getName(), is(post2.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        Post post1 = new Post(0, "Java Job1", true, new City(1, "Москва"));
        Post post2 = new Post(0, "Java Job2", true, new City(1, "Москва"));
        Post post3 = new Post(0, "Java Job3", true, new City(1, "Москва"));
        store.add(post1);
        store.add(post2);
        store.add(post3);
        List<Post> posts = List.of(post1, post2, post3);
        List<Post> postsInDb = store.findAll();
        assertThat(postsInDb, is(posts));
    }
}