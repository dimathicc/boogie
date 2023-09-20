package com.dimathicc.catsgram.dao;

import com.dimathicc.catsgram.model.Post;
import com.dimathicc.catsgram.model.User;

import java.util.List;

public interface FollowDao {
    List<Post> getFollowFeed(String userId, int max);
}
