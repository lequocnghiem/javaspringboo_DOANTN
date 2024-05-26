package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.Repository.Role.RoleRepository;
import com.api.thuctaptotnghiepbackend.Repository.User.UserRepository;
import com.api.thuctaptotnghiepbackend.Request.RegisterEmail;
import com.api.thuctaptotnghiepbackend.Responses.RegisterResponse;
import com.api.thuctaptotnghiepbackend.Service.UserService;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder; 

    @Override
    public User createUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public void addToUser(String email, List<String> roleNames) {
        Optional<User> userOptional = userRepository.findByEmail(email);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getRoles().clear();
            // Lặp qua danh sách các tên vai trò và thêm chúng vào người dùng
            for (String roleName : roleNames) {
                Optional<Role> roleOptional = roleRepository.findByName(roleName);
                
                if (roleOptional.isPresent()) {
                    Role role = roleOptional.get();
                    user.getRoles().add(role);
                } else {
                    System.out.println("Không tìm thấy vai trò với tên: " + roleName);
                }
            }
            
            userRepository.save(user); // Lưu thay đổi vào cơ sở dữ liệu
        } else {
            System.out.println("Không tìm thấy người dùng với email: " + email);
        }
    }
    
    

    @Override
    public Role saveRole (Role role) {
     
        return roleRepository.save(role);
    }


    @Override
    public RegisterResponse register(RegisterEmail registerRequest) {
        Optional<User> existingUserOptional = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (existingUser.isVerified()) {
                throw new RuntimeException("User Already Registered");
            }
        }
        
        User user = User.builder()
                .image(registerRequest.getImage())
                .phone(registerRequest.getPhone())
                .status(registerRequest.getStatus())
                .address(registerRequest.getAddress())
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        String otp = generateOTP();
        user.setOtp(otp);
    
        User savedUser = userRepository.save(user);
        sendVerificationEmail(savedUser.getEmail(), otp);
    
        RegisterResponse response = RegisterResponse.builder()
                .email(user.getEmail())
                .build();
        return response;
    }



    @Override
    public void verify(String email, String otp) {
        User users = userRepository.findByEmail(email).get();
        if (users == null){
            throw new RuntimeException("User not found");
        } else if (users.isVerified()) {
            throw new RuntimeException("User is already verified");
        } else if (otp.equals(users.getOtp())) {
            users.setVerified(true);
            userRepository.save(users);
        }else {
            throw new RuntimeException("Internal Server error");
        }
    }

  private String generateOTP(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email,String otp){
        String subject = "Email verification";
        String body ="your verification otp is: "+otp;
        emailService.sendEmail(email,subject,body);
    }



    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }


    public Role getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElse(null);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();
            existingRole.setName(roleDetails.getName()); // Update other fields as needed
            return roleRepository.save(existingRole);
        }
        return null; // Role with given id not found
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
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
            // updatedUser.setRole(user.getRole());

            return userRepository.save(updatedUser);
        } else {
            // Xử lý khi người dùng không tồn tại
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }



    


}
