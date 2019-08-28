package com.leokongwq.springboot.learn.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kongwenqiang
 */
@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 100)
    private String title;

    @Column
    @NotNull
    @Lob
    private String content;

    /**
     * 双向关联 one-to-many
     * mappedBy = "post" 表示和 PostComment 的 post 字段关联
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
//    @JsonBackReference
    @JsonIgnore
    private Set<PostComment> comments = new HashSet<>();
}
