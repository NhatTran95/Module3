package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.User;

import java.util.List;

public interface IUserService {
    User findUserByUserName(String username);

    void createUser(User user);

    void updateUser(int id, User user);
    List<User> findRoleUser();
}
