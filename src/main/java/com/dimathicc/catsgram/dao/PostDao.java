package com.dimathicc.catsgram.dao;

import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostDao {
    Post addPost(Post post);
    Collection<Post> findAllByUser(User user);
}
