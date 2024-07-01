package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.Request.RegisterEmail;
import com.api.thuctaptotnghiepbackend.Responses.RegisterResponse;

public interface UserService {
    User createUser(User user);
    RegisterResponse register(RegisterEmail registerRequest);
    void verify(String email, String otp);
    Role saveRole(Role role);
    User getUserById(Long userId);
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(User user);
    void deleteUser(Long userId);
    Page<Role> getAllRoles(Pageable pageable);
    void addToUser(String email, List<String> roleNames);
     Role getRoleById(Long id);
    Role updateRole(Long id, Role roleDetails);
    void deleteRole(Long id);
   String getEmailByUserId(Long userId);
}

