package dev.bruno.Challenge.DTOs;

public class CreateCommentDTO {
    private String comment;
    private long idPost;
    private long idUser;

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

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
