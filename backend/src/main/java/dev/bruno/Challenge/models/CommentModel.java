package dev.bruno.Challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("COMMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    @Id @GeneratedValue
    private Long id;
    private String comment;
    @Relationship("RELATED_TO")
    private PostsModel post;

    @Relationship("WAS_COMMENTED_BY")
    private UsersModel user;

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

    public PostsModel getPost() {
        return post;
    }

    public void setPost(PostsModel post) {
        this.post = post;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }
}
