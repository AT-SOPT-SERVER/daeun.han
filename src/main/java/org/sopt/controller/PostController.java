// PostController.java
package org.sopt.controller;

import org.sopt.dto.*;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
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

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestHeader("userId") Long userId,
            @RequestBody PostRequest request
    ) {
        postService.createPost(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<PostSummaryResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long postId,
            @RequestHeader("userId") Long userId,
            @RequestBody PostRequest request
    ) {
        postService.updatePost(postId, request, userId);
        return ResponseEntity.noContent().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader("userId") Long userId
    ) {
        postService.deletePost(postId, userId);
        return ResponseEntity.noContent().build();
    }
}
