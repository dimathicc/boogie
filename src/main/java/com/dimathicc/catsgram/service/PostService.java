package com.dimathicc.catsgram.service;

import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(int id) {
        return posts.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
    }


}
