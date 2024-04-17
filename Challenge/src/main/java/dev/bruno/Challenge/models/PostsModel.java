package dev.bruno.Challenge.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node("POSTS")
public class PostsModel {
    @Id @GeneratedValue
    private Long id;

    private String content;
    @Relationship("WAS_CREATED_BY")
    private UsersModel creator;

    @Relationship("WAS_LIKED_BY")
    private List<UsersModel> likes = new ArrayList<>();

    @Relationship("HAS_COMMENTS")
    private List<CommentModel> comments = new ArrayList<>();

    public PostsModel() {
    }

    public PostsModel(String content, UsersModel creator) {
        this.content = content;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UsersModel getCreator() {
        return creator;
    }

    public void setCreator(UsersModel creator) {
        this.creator = creator;
    }

    public List<UsersModel> getLikes() {
        return likes;
    }

    public void setLikes(List<UsersModel> likes) {
        this.likes = likes;
    }
    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public void addLikeToPost(UsersModel model) {
        this.likes.add(model);
    }

    public void removeLikeFromPost(UsersModel model) {
        for (int userLikePosition = 0; userLikePosition < this.likes.size(); userLikePosition++) {
            if(this.likes.get(userLikePosition).getUsername().equals(model.getUsername())) {
                this.likes.remove(userLikePosition);
                return;
            }
        }
    }

    public void addCommentToPost(CommentModel model) {
        this.comments.add(model);
    }
}
