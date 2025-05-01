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
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Post(String title) {
        validateTitle(title); // 제목 검증
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // 제목 검증 메서드 (private)
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() > 30) {
            throw new CustomException(ErrorCode.INVALID_TITLE);
        }
    }

    // 게시글 제목 수정 시에도 유효성 검증을 재사용하도록 함
    public void updateTitle(String newTitle) {
        validateTitle(newTitle); // 제목 검증
        this.title = newTitle;
    }
}
