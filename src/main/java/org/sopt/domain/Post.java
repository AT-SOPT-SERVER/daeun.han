package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.global.CustomException;
import org.sopt.global.ErrorCode;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Post(String title, String content) {
        validatePost(title, content);
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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

    // 게시글 제목 수정 시에도 유효성 검증을 재사용하도록 함
    public void updatePost(String newTitle, String newContent) {
        validatePost(newTitle, newContent);
        this.title = newTitle;
        this.content = newContent;
    }
}
