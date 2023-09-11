package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/post/{postId}")
    public Optional<Post> findById(@PathVariable int id) {
        return postService.findById(id);
    }
}
