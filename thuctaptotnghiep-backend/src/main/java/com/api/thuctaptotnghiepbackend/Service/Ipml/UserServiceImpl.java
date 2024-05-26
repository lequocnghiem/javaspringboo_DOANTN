package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.Repository.User.UserRepository;
import com.api.thuctaptotnghiepbackend.Service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(User user) {
        // Kiểm tra tồn tại người dùng trước khi cập nhật
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            // Cập nhật thông tin người dùng
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setImage(user.getImage());
          
            updatedUser.setUpdatedAt(user.getUpdatedAt());
           
            updatedUser.setStatus(user.getStatus());
            updatedUser.setRole(user.getRole());

            return userRepository.save(updatedUser);
        } else {
            // Xử lý khi người dùng không tồn tại
            return null;
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
