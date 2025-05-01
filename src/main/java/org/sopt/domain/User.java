package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.global.CustomException;
import org.sopt.global.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> diaryEntities = new ArrayList<>();

    protected User() {}

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new CustomException(ErrorCode.USER_NAME_NULL);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
