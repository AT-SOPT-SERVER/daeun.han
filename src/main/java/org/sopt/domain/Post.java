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

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
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
    public String getAuthorName() { return user.getName(); }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public User getUser() {
        return user;
    }
}
