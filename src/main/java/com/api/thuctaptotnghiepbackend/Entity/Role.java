package com.api.thuctaptotnghiepbackend.Entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@AllArgsConstructor

@Table(name = "role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	

	private String name;
    
  @ManyToMany (mappedBy = "roles")
@Fetch (value = FetchMode. SELECT)
@JsonIgnore
private Set<User> user = new HashSet<>();

    
    public Role(String name) {
        this.name=name;
    }

    public Role() {
    }

}