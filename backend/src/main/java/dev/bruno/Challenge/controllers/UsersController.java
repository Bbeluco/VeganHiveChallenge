package dev.bruno.Challenge.controllers;

import dev.bruno.Challenge.DTOs.LoginInfoDTO;
import dev.bruno.Challenge.DTOs.ErrorMessageDTO;
import dev.bruno.Challenge.DTOs.UserCreatedResponseDTO;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.services.JwtService;
import dev.bruno.Challenge.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserServices userServices;
    private final JwtService jwtService;

    public UsersController(UserServices userServices, JwtService jwtService) {
        this.userServices = userServices;
        this.jwtService = jwtService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers() {
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody LoginInfoDTO dto) {
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInfoDTO dto) {
        UsersModel user = userServices.loginUser(dto);
        if(user == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User or password incorrect");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        UserCreatedResponseDTO responseDTO = new UserCreatedResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setJwtToken(jwtService.generateToken(user));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
