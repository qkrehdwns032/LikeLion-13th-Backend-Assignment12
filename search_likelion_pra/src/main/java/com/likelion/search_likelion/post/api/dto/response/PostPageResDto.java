package com.likelion.search_likelion.post.api.dto.response;

import com.likelion.search_likelion.global.api.dto.response.PaginationDto;

import java.util.List;

public record PostPageResDto(
        List<PostSummaryResDto> posts,
        PaginationDto pagination
) {
}
