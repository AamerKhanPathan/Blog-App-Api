package com.bikkadit.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	  Page<Post> findAllByUser(User user, Pageable pageable);
	  
	  Page<Post> findAllByCategory(Category category,Pageable pageable);
	  
	  List<Post> findByPostTitleContaining(String title);
	  
	  

}
