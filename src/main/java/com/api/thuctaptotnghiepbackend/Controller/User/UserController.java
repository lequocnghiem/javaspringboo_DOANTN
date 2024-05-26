package com.api.thuctaptotnghiepbackend.Controller.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.Request.LoginRequest;
// import com.api.thuctaptotnghiepbackend.Request.LoginRequest;
import com.api.thuctaptotnghiepbackend.Request.RegisterEmail;
import com.api.thuctaptotnghiepbackend.Request.Roleaddtouser;
import com.api.thuctaptotnghiepbackend.Responses.LoginReponse;
// import com.api.thuctaptotnghiepbackend.Responses.LoginReponse;
import com.api.thuctaptotnghiepbackend.Responses.RegisterResponse;
import com.api.thuctaptotnghiepbackend.Service.LoginService;
// import com.api.thuctaptotnghiepbackend.Service.LoginService;
import com.api.thuctaptotnghiepbackend.Service.MenuService;
import com.api.thuctaptotnghiepbackend.Service.UserService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class UserController {

    private  UserService userService;
private final LoginService loginService;
    



@GetMapping("/roleuser")
public ResponseEntity<?> Loginadmin() {
    return ResponseEntity.ok("login user  succeded");
}
    


  @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterEmail registerRequest){
        RegisterResponse registerResponse = userService.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email,@RequestParam String otp){
        try {
            userService.verify(email, otp);
            return new ResponseEntity<>("User verified successfully",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-role")
    public ResponseEntity<String> addRoleToUser(@RequestBody Roleaddtouser userRoleRequest) {
        try {
       
            userService.addToUser(userRoleRequest.getEmail(), userRoleRequest.getRolename());
            return ResponseEntity.ok("Role added successfully to the user.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add role to user.");
        }
    }


    @GetMapping("/role")
    public ResponseEntity<Page<Role>> getAllRoles(@RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Role> userPage = userService.getAllRoles(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }
    
    
  

    @PostMapping("/role")
    public ResponseEntity<Role>role(@RequestBody  Role Role ){
    
        Role savedrole = userService.saveRole(Role);  // Replace CategoryService with TopicService
        return new ResponseEntity<>(savedrole, HttpStatus.CREATED);
    }


    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = userService.getRoleById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        Role updatedRole = userService.updateRole(id, roleDetails);
        if (updatedRole != null) {
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        userService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
















    @PostMapping("/login")
    public ResponseEntity<LoginReponse>login(@RequestBody  LoginRequest loginRequest ){
       return  ResponseEntity.ok(loginService.authenticate(loginRequest));
    }
   

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
           user.setCreatedAt(new Date());
        User createdUser = userService.createUser(user);
       
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getAllUsers(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
