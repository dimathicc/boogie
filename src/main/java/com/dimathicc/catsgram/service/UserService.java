package com.dimathicc.catsgram.service;

import com.dimathicc.catsgram.model.User;
import com.dimathicc.catsgram.exceptions.InvalidEmailException;
import com.dimathicc.catsgram.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst();
    }

    public Optional<User> findByUserId(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public User create(User user) {
        if (users.contains(user)) {
            throw new UserAlreadyExistsException("can't create user");
        }
        users.add(user);
        return user;
    }

    public User update(User updatedUser) {
        if (updatedUser.getEmail().isEmpty()) {
            throw new InvalidEmailException("can't update user info");
        } else if (!users.contains(updatedUser)) {
            users.add(updatedUser);
            return updatedUser;
        } else {
            return updatedUser;
        }
    }

}
