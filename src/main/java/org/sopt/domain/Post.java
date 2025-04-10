package org.sopt.domain;

public class Post {

    //private이라는 접근 제어자를 이용하면  => 캡슐화로 인해. getter.setter 등을 사용한다.
    private int id;
    private String title;
    public Post(int id, String title){

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다. 제목이 비어 있는 경우에는 게시글 작성이 되지 않습니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자를 넘지 않게 해주세요.제목이 너무 긴 경우에는 게시글 작성이 되지 않습니다.");
        }

        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String updatedTitle) {
        this.title = updatedTitle;
    }
}
