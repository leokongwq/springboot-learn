package com.leokongwq.springboot.learn.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author kongwenqiang
 */
@Component
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Optional<PostComment> findById(Long id);

    Page<PostComment> findByPostId(Long postId, Pageable pageable);

    Optional<PostComment> findByIdAndPostId(Long id, Long postId);
}
