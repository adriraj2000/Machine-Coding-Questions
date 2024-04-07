package org.example;

public class Post {
    private String postId;

    public Post(String postId, int postNumber) {
        this.postId = postId;
        this.postNumber = postNumber;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    private int postNumber;
}
