package com.likelion.search_likelion.post.api.dto.response;

import com.likelion.search_likelion.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostSummaryResDto(
    Long post_id,
    String title,
    LocalDate createAt
){
    public static PostSummaryResDto from(Post post){
        return PostSummaryResDto.builder()
            .post_id(post.getPostId())
            .title(post.getTitle())
            .createAt(post.getCreateAt())
            .build();
    }
}
