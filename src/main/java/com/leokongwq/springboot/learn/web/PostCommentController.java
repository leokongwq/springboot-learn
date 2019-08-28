package com.leokongwq.springboot.learn.web;

import com.leokongwq.springboot.learn.jpa.PostComment;
import com.leokongwq.springboot.learn.jpa.PostCommentRepository;
import com.leokongwq.springboot.learn.jpa.PostsRepository;
import com.leokongwq.springboot.learn.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kongwenqiang
 */
@RestController
public class PostCommentController {

    @Autowired
    private PostCommentRepository commentRepository;

    @Autowired
    private PostsRepository postRepository;

    @PostMapping("/posts/{postId}/comments")
    public PostComment createComment(@PathVariable (value = "postId") Long postId,
                                     @Valid @RequestBody PostComment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostComment not found with id " + commentId + " and postId " + postId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public PostComment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody PostComment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(commentRequest.getContent());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @GetMapping("/posts/{postId}/comments")
    public Page<PostComment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public PostComment getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
            .orElseThrow(() -> new ResourceNotFoundException("PostComment not found with id " + commentId + " and postId " + commentId));
    }
}
