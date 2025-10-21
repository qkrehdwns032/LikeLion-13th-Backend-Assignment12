package com.likelion.search_likelion.post.api.dto.response;

import com.likelion.search_likelion.post.domain.Category;
import com.likelion.search_likelion.post.domain.Post;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostInfoResDto(
    String title,
    String content,
    Category category,
    LocalDate createAt
) {
    public static PostInfoResDto from(Post post){
        return PostInfoResDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .category(post.getCategory())
            .createAt(post.getCreateAt())
            .build();
    }
}
