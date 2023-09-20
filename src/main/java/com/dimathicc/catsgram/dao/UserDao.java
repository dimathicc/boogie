package com.dimathicc.catsgram.dao;

import com.dimathicc.catsgram.model.User;

import java.util.Optional;

public interface UserDao {
    User addUser(User user);
    Optional<User> findUserById(String id);
}
