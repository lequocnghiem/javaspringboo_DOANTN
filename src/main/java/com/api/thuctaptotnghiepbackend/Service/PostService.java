package com.api.thuctaptotnghiepbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Post;

public interface PostService {
      Post createPost(Post post);

    Post getPostById(Long postId);

    Page<Post> getAllPosts(Pageable pageable);

    Post updatePost(Post post);

    void deletePost(Long postId);
    Page<Post> getPostsByTopicId(int limit, Long topicId, Pageable pageable);
}
