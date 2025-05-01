package org.sopt.domain;

import jakarta.persistence.*;

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
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다. 제목이 비어 있는 경우에는 게시글 작성이 되지 않습니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자를 넘지 않게 해주세요. 제목이 너무 긴 경우에는 게시글 작성이 되지 않습니다.");
        }
    }

    // 게시글 제목 수정 시에도 유효성 검증을 재사용하도록 함
    public void updateTitle(String newTitle) {
        validateTitle(newTitle); // 제목 검증
        this.title = newTitle;
    }
}
