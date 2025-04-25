package org.sopt.domain;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    protected Post() {
        // JPA는 기본 생성자가 꼭 필요
    }

    public Post(String title) {
        // 유효성 검사 추가
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이하여야 합니다.");
        }
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //setter 대신 updateTitle 같은 도메인 메서드를 사용하는 이유?
    //setter는 단순히 필드를 변경할 뿐 아무 의미도 맥락도 부여하지 않음.
    //반면 updateTitle은 "게시글 제목을 수정하는 행위"라는 도메인의 의미를 담은 행위 메서드
    public void updateTitle(String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        this.title = newTitle;
    }
}
