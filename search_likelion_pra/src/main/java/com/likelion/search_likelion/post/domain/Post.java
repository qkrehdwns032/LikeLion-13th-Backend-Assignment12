package com.likelion.search_likelion.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;
    private String content;
    private LocalDate createAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Category category;



    @Builder
    private Post(String title, String content, Category category, LocalDate createAt) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.category = category;

    }
}