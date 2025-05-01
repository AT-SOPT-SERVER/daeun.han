package org.sopt.controller;

import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.sopt.domain.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 생성
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Post post = postService.createPost(request.title(), request.content());
        return ResponseEntity.ok(post);
    }

    // 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시물 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // 게시물 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        boolean updated = postService.updatePost(id, request.title(), request.content());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }
}
