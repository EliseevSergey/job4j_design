package ru.job4j.gc.leak;

import java.util.*;

public class PostStore {

    private static List<Post> posts = new ArrayList<>();
    private static int qty = 0;


    public Post add(Post post) {
        int id = qty++;
        post.setId(id);
        posts.add(post);
        System.out.println(post.getId());
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public static List<Post> getPosts() {
        return posts;
    }

}