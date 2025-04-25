package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시물 생성
    public Post createPost(String title) {
        Post post = new Post(title);
        return postRepository.save(post);
    }

    // 모든 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시물 ID로 조회
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 게시물 제목 수정
    public boolean updatePostTitle(Long id, String newTitle) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateTitle(newTitle);
            postRepository.save(post);
            return true;
        }
        return false;
    }

    // 게시물 삭제
    public boolean deletePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
