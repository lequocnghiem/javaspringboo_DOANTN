package com.api.thuctaptotnghiepbackend.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.JWT.JwtService;
import com.api.thuctaptotnghiepbackend.Repository.Role.RoleCustomRepo;
import com.api.thuctaptotnghiepbackend.Repository.User.UserRepository;
import com.api.thuctaptotnghiepbackend.Request.LoginRequest;
import com.api.thuctaptotnghiepbackend.Responses.LoginReponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository  userRepository;
    private final AuthenticationManager authenticationManager ;
    private final RoleCustomRepo roleCustomRepo ;
    private final JwtService jwtService ;
 


    public LoginReponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        if (!user.isVerified()) {
            throw new RuntimeException("Tài khoản chưa được xác minh");
        }
        List <Role> role=null;
       if (user!=null) {
    role=roleCustomRepo.getRoles(user);
}
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Set<Role> set = new HashSet<>();
            role.stream().forEach (c->set.add(new Role (c.getName())));
            user.setRoles (set);
            set.stream().forEach (i->authorities.add(new SimpleGrantedAuthority (i.getName())));
            var jwtToken = jwtService.generateToken(user,authorities);
            var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);
            return LoginReponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}  
