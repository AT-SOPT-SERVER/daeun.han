package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
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

    public Post createPost(PostRequest request) {
        Post post = new Post(request.getTitle());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    public boolean updatePostTitle(Long id, String newTitle) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateTitle(newTitle); // 내부 검증 포함
            postRepository.save(post);
            return true;
        }
        return false;
    }

    public boolean deletePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

}