package dev.bruno.Challenge.services;

import dev.bruno.Challenge.models.PostsModel;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UsersModel addUser(UsersModel user) {
        UsersModel save = userRepository.save(user);

        return save;
    }

    public UsersModel doUserExists(long idUser) {
        UsersModel user = userRepository.findUserById(idUser);
        return user;
    }
}
