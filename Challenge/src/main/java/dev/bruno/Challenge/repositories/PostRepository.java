package dev.bruno.Challenge.repositories;

import dev.bruno.Challenge.models.PostsModel;
import dev.bruno.Challenge.models.UsersModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends Neo4jRepository<PostsModel, Long> {
    PostsModel findPostById(Long id);
}
