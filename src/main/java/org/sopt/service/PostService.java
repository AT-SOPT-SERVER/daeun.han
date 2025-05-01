// PostService.java
package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.*;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Long createPost(Long userId, PostRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        validatePost(request.getTitle(), request.getContent());

        Post post = new Post(request.getTitle(), request.getContent(), user);
        return postRepository.save(post).getId();
    }

    // 게시글 전체 조회 (최신 순)
    public List<PostSummaryResponse> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByIdDesc();  // 최신 순으로 게시글 조회
        return posts.stream()
                .map(post -> new PostSummaryResponse(post.getTitle(), post.getUser().getName()))  // 제목과 작성자 이름을 DTO로 변환
                .collect(Collectors.toList());
    }

    public PostDetailResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return new PostDetailResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthorName());
    }

    @Transactional
    public void updatePost(Long postId, PostRequest request, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_POST_ACCESS);
        }

        validatePost(request.getTitle(), request.getContent());

        post.update(request.getTitle(), request.getContent());
    }

    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_POST_ACCESS);
        }

        postRepository.delete(post);
    }

    // 제목+내용 한 번에 검증
    private void validatePost(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new CustomException(ErrorCode.TITLE_NULL);
        }
        if (title.length() > 30) {
            throw new CustomException(ErrorCode.TITLE_TOO_LONG);
        }
        if (content == null || content.trim().isEmpty()) {
            throw new CustomException(ErrorCode.CONTENT_NULL);
        }
    }
}
