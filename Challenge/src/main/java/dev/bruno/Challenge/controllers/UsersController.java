package dev.bruno.Challenge.controllers;

import dev.bruno.Challenge.DTOs.CreateUserDTO;
import dev.bruno.Challenge.DTOs.ErrorMessageDTO;
import dev.bruno.Challenge.DTOs.UserCreatedResponseDTO;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.services.JwtService;
import dev.bruno.Challenge.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserServices userServices;
    private final JwtService jwtService;

    public UsersController(UserServices userServices, JwtService jwtService) {
        this.userServices = userServices;
        this.jwtService = jwtService;
    }


    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers() {
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody CreateUserDTO dto) {
        UsersModel newUser = userServices.addUser(dto);
        if(newUser == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User already exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
        }

        UserCreatedResponseDTO responseDTO = new UserCreatedResponseDTO();
        responseDTO.setId(newUser.getId());
        responseDTO.setUsername(newUser.getUsername());
        responseDTO.setJwtToken(jwtService.generateToken(newUser));

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
