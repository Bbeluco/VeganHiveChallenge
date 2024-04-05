package dev.bruno.Challenge.controllers;

import dev.bruno.Challenge.DTOs.*;
import dev.bruno.Challenge.models.CommentModel;
import dev.bruno.Challenge.models.PostsModel;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.services.JwtService;
import dev.bruno.Challenge.services.PostService;
import dev.bruno.Challenge.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostsController {
    private final PostService postService;
    private final UserServices userServices;
    private final JwtService jwtService;

    @Autowired
    public PostsController(PostService postService, UserServices userServices, JwtService jwtService) {
        this.postService = postService;
        this.userServices = userServices;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> createNewPost(@RequestBody PostCreationDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(authToken);

        UsersModel creator = userServices.doUserExists(username);
        if(creator == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel newPost = new PostsModel(dto.getContent(), creator);

        return new ResponseEntity<>(postService.createOrUpdatePost(newPost), HttpStatus.CREATED);
    }

    @PutMapping("/likes")
    public ResponseEntity<?> likePost(@RequestBody LikesDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(authToken);

        UsersModel user = userServices.doUserExists(username);
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

    @PostMapping("/comment")
    public ResponseEntity<?> commentOnPost(@RequestBody CreateCommentDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(authToken);

        UsersModel userThatWillCommentOnPost = userServices.doUserExists(username);
        if(userThatWillCommentOnPost == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel post = postService.doPostExists(dto.getIdPost());
        if(post == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Post do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        CommentModel comment = postService.commentOnPost(userThatWillCommentOnPost, post, dto.getComment());


        return new ResponseEntity<>(new CommentResponseDTO(comment.getId()
                , comment.getComment()
                , comment.getPost().getId()
                , userThatWillCommentOnPost.getUsername()), HttpStatus.CREATED);

    }
}
