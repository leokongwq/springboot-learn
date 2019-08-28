package com.leokongwq.springboot.learn.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author kongwenqiang
 */
@Entity
@Table(name = "post_comment")
@Getter
@Setter
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Lob
    private String content;

    // 单向关联 - 推荐使用
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "post_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Post post;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonManagedReference
    @JsonIgnore
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostComment that = (PostComment) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(content, that.content) &&
            Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, post);
    }
}
