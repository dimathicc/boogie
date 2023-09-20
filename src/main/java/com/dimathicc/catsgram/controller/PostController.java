package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @GetMapping
    public Collection<Post> findAll(@RequestParam String userId) {
        return postService.findPostsByUser(userId);
    }

}
