package dev.bruno.Challenge.DTOs;

public class CreateCommentDTO {
    private String comment;
    private long idPost;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getIdPost() {
        return idPost;
    }

    public void setIdPost(long idPost) {
        this.idPost = idPost;
    }
}
