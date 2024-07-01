package com.api.thuctaptotnghiepbackend.Entity;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@Data

@Entity
@Table(name = "db_user")
@Builder
//
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   


	private String name;

    @Lob
    private byte[] image;
    private String phone;
    private String username;
    private String password;
    private String address;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private String otp;
    private boolean verified;
    // Các phương thức getter và setter


    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //  private List<Cart> Carts;


    //  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //  private List<Contact> Contacts;


     public User(){

	 }
     
     public User(Long id, String name, byte[] image, String phone, String username, String password, String address,
 			String email, Date createdAt, Date updatedAt, String status, Set<Role> roles, String otp, boolean verified
 			) {
 		super();
 		this.id = id;
 		this.name = name;
 		this.image = image;
 		this.phone = phone;
 		this.username = username;
 		this.password = password;
 		this.address = address;
 		this.email = email;
 		this.createdAt = createdAt;
 		this.updatedAt = updatedAt;
 		this.status = status;
 		this.roles = roles;
 		this.otp = otp;
 		this.verified = verified;
 		
 	} 
     
  
	 public String getName() {
		return name;
	}
     
     
	public User(String username) {
        this.username = username;
    }
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	if(roles==null){
		return new ArrayList<>();
	}
    return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
   
}
