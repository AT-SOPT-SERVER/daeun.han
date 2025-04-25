package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post") // @PostMapping 어노테이션을 붙임으로서 HTTP 요청을 객체로 변환 (요청을 자동으로 분석해서 어떤 메서드를 호출할지 결정)
    public void createPost(@RequestBody final PostRequest postRequest) {
        // @RequestBody 를 붙이면 JSON 형식으로 온 요청 데이터를 PostRequest에 자동으로 매핑(request 의 body 내에 있는 값 역시 객체로 변환)
        postService.createPost(postRequest.getTitle());
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }
    public Boolean updatePostTitle(int id, String newTitle) {
        return postService.updatePostTitle(id, newTitle);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return null;
    }

}