package org.sopt.domain;

public class Post {

    //private이라는 접근 제어자를 이용하면  => 캡슐화로 인해. getter.setter 등을 사용한다.
    private int id;
    private String title;
    public Post(int id, String title){
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
