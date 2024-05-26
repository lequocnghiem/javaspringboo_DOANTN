package com.api.thuctaptotnghiepbackend.Repository.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> getPostsByTopicId(int limit, Long topicId, Pageable pageable);
}
