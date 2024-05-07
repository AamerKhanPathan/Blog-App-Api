package com.bikkadit.blog.entities;


import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name",nullable = false,length = 50 )
	private String name;
	
	@Column(name="user_email",unique = true)
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private List<Post> posts=new ArrayList<>();
	
	@OneToMany (mappedBy = "user")
	private List<Comment> comments;
	

}
