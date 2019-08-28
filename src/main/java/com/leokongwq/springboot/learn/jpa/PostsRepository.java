package com.leokongwq.springboot.learn.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author kongwenqiang
 */
@Component
public interface PostsRepository extends JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post> {

    Optional<Post> findById(Long postId);

    boolean existsById(Long postId);

}
