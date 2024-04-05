package dev.bruno.Challenge.DTOs;

public class CommentResponseDTO {
    private Long id;
    private String comment;
    private Long idPost;
    private String username;

    public CommentResponseDTO(Long id, String comment, Long idPost, String username) {
        this.id = id;
        this.comment = comment;
        this.idPost = idPost;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
