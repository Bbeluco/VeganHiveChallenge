package dev.bruno.Challenge.DTOs;

import java.util.ArrayList;
import java.util.List;

public class GetAllCommentsDTO {
    private List<CommentAvailableDTO> comments = new ArrayList<>();

    public List<CommentAvailableDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentAvailableDTO> comments) {
        this.comments = comments;
    }

    public void addCommentToList(String creator, String content) {
        this.comments.add(new CommentAvailableDTO(creator, content));
    }
}

