package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private int postId = 1;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title) {
        // 예외가 발생하지 않을 때만 postId++ 하도록 수정
        Post post = new Post(postId, title);

        postRepository.save(post);
        postId++; // 성공적으로 저장된 후에 증가

        System.out.println(post.getTitle());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public Boolean updatePostTitle(int id, String newTitle) {
        Post post = postRepository.findPostById(id);
        if (post == null) {
            return false; // 해당 ID의 게시물이 없을 경우
        }

        post.setTitle(newTitle);
        return true;
    }
}