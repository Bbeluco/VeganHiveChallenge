package dev.bruno.Challenge.DTOs;

public class PostCreationDTO {
    private String content;
    private long creator;

    public PostCreationDTO() {
    }

    public PostCreationDTO(String content, long creator) {
        this.content = content;
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }
}
