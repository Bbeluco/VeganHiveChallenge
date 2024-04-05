package dev.bruno.Challenge.repositories;

import dev.bruno.Challenge.models.CommentModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepository extends Neo4jRepository<CommentModel, Long> {
}
