package dev.bruno.Challenge.DTOs;

import java.util.ArrayList;
import java.util.List;

public class AllPostsResponseDTO {
    private List<PostDetailsDTO> posts = new ArrayList<>();

    public List<PostDetailsDTO> getPosts() {
        return posts;
    }

    public void addPostToResponse(PostDetailsDTO post) {
        posts.add(post);
    }
}

