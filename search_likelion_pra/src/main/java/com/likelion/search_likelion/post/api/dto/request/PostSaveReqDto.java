package com.likelion.search_likelion.post.api.dto.request;

import com.likelion.search_likelion.post.domain.Category;

import java.time.LocalDate;

public record PostSaveReqDto(
    Category category,
    String title,
    String content
) {
}
