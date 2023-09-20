package com.dimathicc.catsgram.service;

import com.dimathicc.catsgram.dao.PostDao;
import com.dimathicc.catsgram.exceptions.UserNotFoundException;
import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;


    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Post addPost(Post post) {
        return postDao.addPost(post);
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));

        return postDao.findAllByUser(user);
    }

    public Collection<Post> findPostsByUser(String authorId, Integer size, String sort) {
        return findPostsByUser(authorId)
                .stream()
                .sorted((p0, p1) -> {
                    int comp = p0.getCreationDate().compareTo(p1.getCreationDate());
                    if (sort.equals("desc")) {
                        comp = -1 * comp;
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

}
