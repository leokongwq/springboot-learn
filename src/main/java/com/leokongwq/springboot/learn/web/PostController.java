package com.leokongwq.springboot.learn.web;

import com.leokongwq.springboot.learn.jpa.Post;
import com.leokongwq.springboot.learn.jpa.PostsRepository;
import com.leokongwq.springboot.learn.web.exception.ResourceNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
 * https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
 * https://www.baeldung.com/spring-data-web-support
 * @author kongwenqiang
 */
@RestController
public class PostController {

    @Resource
    private PostsRepository postsRepository;

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postsRepository.save(post);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postsRepository.findById(postId).map(post -> {
            postsRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postsRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            return postsRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @GetMapping("/posts/{postId}")
    @ResponseBody
    public Post getById(@PathVariable(name = "postId") Long postId) {
        return postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

//    /**
//     * 魔法在于 {@link org.springframework.data.repository.support.DomainClassConverter}
//     */
//    @GetMapping("/posts/{postId}")
//    @ResponseBody
//    public Post getByPostId(@PathVariable(name = "postId") Post post) {
//        return post;
//    }

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
//        Pageable pageable = new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "title"));
        return postsRepository.findAll(pageable);
    }

    /**
     * 魔法在于  querydsl-apt, querydsl-jpa
     */
    @GetMapping("/filterposts")
    public Iterable<Post> getUsersByQuerydslPredicate(@QuerydslPredicate(root = Post.class) Predicate predicate) {
        return postsRepository.findAll(predicate);
    }
}
