package com.api.thuctaptotnghiepbackend;

import java.sql.Date;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;
import com.api.thuctaptotnghiepbackend.Service.UserService;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class ThuctaptotnghiepBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThuctaptotnghiepBackendApplication.class, args);
	}





	@Bean
	BCryptPasswordEncoder brBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
				// userService.saveRole(new Role( "ROLE_USER"));
				// userService.saveRole(new Role( "ROLE_MANAGER"));
				// userService.saveRole(new Role("ROLE_ADMIN"));
				// userService.saveRole(new Role("ROLE_SUPER_ADMIN"));

			// User user = new User();
			// user.setName("Quoc Nghiemm");
			// user.setImage(null);
			// user.setPhone("1234567890");
			// user.setUsername("quocnghiemmmm");
			// user.setPassword("nghiem123"); // Make sure to encode your password
			// user.setAddress("123 Main Street");
			// user.setEmail("quocnghiem@exampleeee.com");
			// user.setCreatedAt(null);
			// user.setUpdatedAt(null);
			// user.setStatus("ACTIVE"); // Or set to whatever default status you prefer
			// user.setRoles(new HashSet<>()); // Assuming getAllRoles() returns all roles
            // userService.createUser(user);

            // userService.addToUser("quocnghiem@exampleeee.com", "ROLE_USER");
           
        };
    }

}
