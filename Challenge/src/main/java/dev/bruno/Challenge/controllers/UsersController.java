package dev.bruno.Challenge.controllers;

import dev.bruno.Challenge.DTOs.CreateUserDTO;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserServices userServices;

    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers() {
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsersModel> createNewUser(@RequestBody CreateUserDTO dto) {
        UsersModel newUser = new UsersModel();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());


        return new ResponseEntity<>(userServices.addUser(newUser), HttpStatus.CREATED);
    }
}
