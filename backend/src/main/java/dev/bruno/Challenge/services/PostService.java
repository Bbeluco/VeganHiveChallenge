package dev.bruno.Challenge.services;

import dev.bruno.Challenge.models.CommentModel;
import dev.bruno.Challenge.models.PostsModel;
import dev.bruno.Challenge.models.UsersModel;
import dev.bruno.Challenge.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostsModel createOrUpdatePost(PostsModel model) {
        return postRepository.save(model);
    }

    public List<PostsModel> getAllPosts() {
        return postRepository.findAllByOrderByCreationDateAsc();
    }

    public PostsModel getSpecificPost(long idPost) {
        PostsModel post = postRepository.findPostById(idPost);
        return post;
    }

    public CommentModel commentOnPost(UsersModel user, PostsModel post, String comment) {
        CommentModel newComment = new CommentModel();
        newComment.setComment(comment);
        newComment.setPost(post);
        newComment.setUser(user);

        post.addCommentToPost(newComment);
        postRepository.save(post);

        return newComment;
    }
}
