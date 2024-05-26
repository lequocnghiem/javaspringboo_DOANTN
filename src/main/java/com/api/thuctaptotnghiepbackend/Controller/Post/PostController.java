package com.api.thuctaptotnghiepbackend.Controller.Post;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.thuctaptotnghiepbackend.Entity.Post;

import com.api.thuctaptotnghiepbackend.Service.PostService;



@RestController
@AllArgsConstructor
@RequestMapping("api/post") // Change the path to "api/posts"
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class PostController {

    private PostService postService; // Rename the service

    // Create Post REST API
   @PostMapping
    public ResponseEntity<Post> createTopic(@RequestBody Post post) {  // Replace Category with Topic
        post.setCreatedAt(LocalDateTime.now());
        Post savedTopic = postService.createPost(post);  // Replace CategoryService with TopicService
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }
    // Get Post by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long postId) { // Rename the method and parameter
        Post post = postService.getPostById(postId); // Rename the service method
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All Posts REST API
    @GetMapping
    public ResponseEntity<Page<Post>> getAllPosts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postService.getAllPosts(pageable); // Rename the service method
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Update Post REST API
    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long postId,
            @RequestBody Post post) { // Rename the method and parameter
        post.setId(postId);
        Post updatedPost = postService.updatePost(post); // Rename the service method
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Delete Post REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId) { // Rename the parameter
        postService.deletePost(postId); // Rename the service method
        return new ResponseEntity<>("Post successfully deleted!", HttpStatus.OK);
    }


    @GetMapping("/{limit}/{topicId}")
    public ResponseEntity<Page<Post>> getPostsByTopicId(@PathVariable int limit, @PathVariable Long topicId, Pageable pageable) {
        Page<Post> posts = postService.getPostsByTopicId(limit, topicId, pageable);
        return ResponseEntity.ok().body(posts);
    }
}
