package dev.bruno.Challenge.services;

import dev.bruno.Challenge.DTOs.LoginInfoDTO;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UsersModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UsersModel addUser(LoginInfoDTO dto) {

        if(doUserExists(dto.getUsername()) != null) {
            return null;
        }

        UsersModel newUser = new UsersModel();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        UsersModel save = userRepository.save(newUser);

        return save;
    }

    public UsersModel doUserExists(String username) {
        Optional<UsersModel> user = userRepository.findUserByUsername(username);
        return user.orElse(null);
    }

    public UsersModel loginUser(LoginInfoDTO dto) {
        Optional<UsersModel> user = userRepository.findUserByUsername(dto.getUsername());
        if(user.isEmpty()) {
            return null;
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            return null;
        }

        return user.get();
    }
}
