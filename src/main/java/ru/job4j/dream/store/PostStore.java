package ru.job4j.dream.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostStore {
    private int ids = 1;
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(ids);
        posts.put(ids++, post);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}
