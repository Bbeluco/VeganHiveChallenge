package dev.bruno.Challenge.repositories;

import dev.bruno.Challenge.models.UsersModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<UsersModel, Long> {

    UsersModel findUserById(Long id);
    Optional<UsersModel> findUserByUsername(String username);
}
