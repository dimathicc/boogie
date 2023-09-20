package com.dimathicc.catsgram.service;

import com.dimathicc.catsgram.dao.UserDao;
import com.dimathicc.catsgram.model.User;
import com.dimathicc.catsgram.exceptions.InvalidEmailException;
import com.dimathicc.catsgram.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public Optional<User> findUserById(String id) {
        return userDao.findUserById(id);
    }
}
