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
}
