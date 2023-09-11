package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostFeedController {

    @PostMapping("/feed/friends")
    public Post friendFeed(@RequestBody String str) {
        return null;
    }

}
