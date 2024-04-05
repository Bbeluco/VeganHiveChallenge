package dev.bruno.Challenge.controllers;

import dev.bruno.Challenge.DTOs.ErrorMessageDTO;
import dev.bruno.Challenge.DTOs.LikesDTO;
import dev.bruno.Challenge.DTOs.PostCreationDTO;
import dev.bruno.Challenge.models.PostsModel;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.repositories.UserRepository;
import dev.bruno.Challenge.services.PostService;
import dev.bruno.Challenge.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostsController {
    private final PostService postService;
    private final UserServices userServices;

    @Autowired
    public PostsController(PostService postService, UserServices userServices) {
        this.postService = postService;
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<?> createNewPost(@RequestBody PostCreationDTO dto) {
        UsersModel creator = userServices.doUserExists(dto.getCreator());
        if(creator == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel newPost = new PostsModel(dto.getContent(), creator);

        return new ResponseEntity<>(postService.createOrUpdatePost(newPost), HttpStatus.CREATED);
    }

    @PutMapping("/likes")
    public ResponseEntity<?> likePost(@RequestBody LikesDTO dto) {
        UsersModel user = userServices.doUserExists(dto.getIdUser());
        if(user == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel post = postService.doPostExists(dto.getIdPost());
        if(post == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Post do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        post.addLikeToPost(user);

        return new ResponseEntity<>(postService.createOrUpdatePost(post), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PostsModel>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
}
