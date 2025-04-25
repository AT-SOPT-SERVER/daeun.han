package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request) {
        try {
            Post post = postService.createPost(request);
            return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePostTitle(@PathVariable Long id, @RequestBody PostRequest request) {
        try {
            boolean updated = postService.updatePostTitle(id, request.getTitle());
            if (updated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        boolean deleted = postService.deletePostById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}