package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;
import java.util.Objects;

public class PostController {

    private PostService postService = new PostService();

    private int postId;

//    public Object selectMethod(int inputCase) {
//        switch (inputCase) {
//            case 1:
//                createPost();
//        }
//    }

    public void createPost(String title) {
        Post post = new Post(postId, title);
        postService.createPost(post);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(int deleteId) {
        return postService.deletePostById(deleteId);
    }
}
