package com.api.thuctaptotnghiepbackend.Service.Ipml;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Post;
import com.api.thuctaptotnghiepbackend.Repository.Post.PostRepository;
import com.api.thuctaptotnghiepbackend.Service.PostService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService { // Rename the class

    private PostRepository postRepository; // Rename the repository

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post updatePost(Post post) {
        Post existingPost = postRepository.findById(post.getId()).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(post.getTitle());
        
            existingPost.setDetail(post.getDetail());
            existingPost.setImage(post.getImage());
            existingPost.setType(post.getType());
          
            existingPost.setCreatedAt(post.getCreatedAt());
            existingPost.setUpdatedAt(post.getUpdatedAt());
          
            existingPost.setStatus(post.getStatus());

            Post updatedPost = postRepository.save(existingPost);
            return updatedPost;
        }
        return null;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }


    @Override
    public Page<Post> getPostsByTopicId(int limit, Long topicId, Pageable pageable) {
        return postRepository.getPostsByTopicId(limit, topicId, pageable);
    }
}