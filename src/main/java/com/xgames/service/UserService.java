package com.xgames.service;

import com.xgames.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    boolean userAlreadyExists(String email);
}
