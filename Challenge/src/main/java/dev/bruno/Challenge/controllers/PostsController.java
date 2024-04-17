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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<?> createNewPost(@RequestBody PostCreationDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(filterHeaderInfo(authToken));

        UsersModel creator = userServices.doUserExists(username);
        if(creator == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel newPost = new PostsModel(dto.getContent(), creator);

        PostsModel post = postService.createOrUpdatePost(newPost);
        PostCreationResponseDTO responseDTO = new PostCreationResponseDTO();
        responseDTO.setId(post.getId());
        responseDTO.setCreator(post.getCreator().getUsername());
        responseDTO.setContent(post.getContent());
        responseDTO.setLikes(post.getLikes().size());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    private String filterHeaderInfo(String authToken) {
        return authToken.split("Bearer ")[1];
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/likes")
    public ResponseEntity<?> likePost(@RequestBody LikesDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(filterHeaderInfo(authToken));

        UsersModel user = userServices.doUserExists(username);
        if(user == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel post = postService.getSpecificPost(dto.getIdPost());
        if(post == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Post do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        if(checkIfWasLikedByCurrentUser(authToken, post.getLikes())) {
            post.removeLikeFromPost(user);
        } else {
            post.addLikeToPost(user);
        }

        postService.createOrUpdatePost(post);

        return ResponseEntity.ok("");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public ResponseEntity<AllPostsResponseDTO> getAllPosts(@RequestHeader("Authorization") String authToken) {
        AllPostsResponseDTO responseDTO = new AllPostsResponseDTO();
        for (PostsModel post : postService.getAllPosts()) {
            PostDetailsDTO details = new PostDetailsDTO();
            details.setId(post.getId());
            details.setCreator(post.getCreator().getUsername());
            details.setContent(post.getContent());
            details.setLikes(post.getLikes().size());
            details.setLikedByMe(checkIfWasLikedByCurrentUser(authToken, post.getLikes()));

            responseDTO.addPostToResponse(details);
        }


        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private boolean checkIfWasLikedByCurrentUser(String authToken, List<UsersModel> usersThatLikedPost) {
        String currentUser = jwtService.extractUsername(filterHeaderInfo(authToken));

        for(UsersModel user : usersThatLikedPost) {
            if(user.getUsername().equals(currentUser)) {
                return true;
            }
        }

        return false;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/comment")
    public ResponseEntity<?> commentOnPost(@RequestBody CreateCommentDTO dto, @RequestHeader("Authorization") String authToken) {
        String username = jwtService.extractUsername(filterHeaderInfo(authToken));

        UsersModel userThatWillCommentOnPost = userServices.doUserExists(username);
        if(userThatWillCommentOnPost == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("User do not exists");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostsModel post = postService.getSpecificPost(dto.getIdPost());
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificPost(@RequestHeader("Authorization") String authToken, @PathVariable long id) {
        PostsModel post = postService.getSpecificPost(id);
        if(post == null) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Post do not exists!");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }

        PostDetailsDTO dto = new PostDetailsDTO();
        dto.setId(post.getId());
        dto.setCreator(post.getCreator().getUsername());
        dto.setContent(post.getContent());
        dto.setLikes(post.getLikes().size());
        dto.setLikedByMe(checkIfWasLikedByCurrentUser(authToken, post.getLikes()));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
