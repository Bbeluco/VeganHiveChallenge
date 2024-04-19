package dev.bruno.Challenge.DTOs;

public class PostCreationDTO {
    private String content;

    public PostCreationDTO() {
    }

    public PostCreationDTO(String content, long creator) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
