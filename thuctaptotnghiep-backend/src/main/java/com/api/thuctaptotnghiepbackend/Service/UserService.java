package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.User;

public interface UserService {
    User createUser(User user);
    User getUserById(String userId);
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(User user);
    void deleteUser(String userId);
}

