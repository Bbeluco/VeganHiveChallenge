package dev.bruno.Challenge.DTOs;

public class CommentAvailableDTO {
    private String creator;
    private String content;

    public CommentAvailableDTO(String creator, String content) {
        this.creator = creator;
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
