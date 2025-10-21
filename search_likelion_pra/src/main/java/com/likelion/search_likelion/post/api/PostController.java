package com.likelion.search_likelion.post.api;

import com.likelion.search_likelion.exception.Response;
import com.likelion.search_likelion.exception.status.SuccessStatus;
import com.likelion.search_likelion.global.api.dto.response.PaginationDto;
import com.likelion.search_likelion.post.api.dto.request.PostSaveReqDto;
import com.likelion.search_likelion.post.api.dto.response.PostInfoResDto;
import com.likelion.search_likelion.post.api.dto.response.PostPageResDto;
import com.likelion.search_likelion.post.api.dto.response.PostSummaryResDto;
import com.likelion.search_likelion.post.application.PostService;
import com.likelion.search_likelion.post.application.SearchService;
import com.likelion.search_likelion.post.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final SearchService searchService;

    //공고 생성
    @PostMapping(value = "/save")
    public ResponseEntity<Response<Long>> postSave(@RequestBody PostSaveReqDto postSaveRequestDto) {
        Response<Long> response = postService.postSave(postSaveRequestDto);
        return ResponseEntity
            .status(SuccessStatus.POST_CREATED.getStatus())
            .body(response);
    }

    //postId로 공고 상세 조회
    @GetMapping("/{postId}")
    public Response<PostInfoResDto> PostFindById(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResponseDto = postService.getPostId(postId);
        return Response.success(SuccessStatus.POST_SUCCESS, postInfoResponseDto);
    }

    //공고 전체 조회(요약 정보)
    @GetMapping
    public Response<PostPageResDto> postFindAll(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String category,
        //PageableDefault로 페이지네이션 기본값 적용, pageable 파라미터 추가
        @PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Category categoryEnum = Category.from(category); //문자열로 받은 뒤, Enum 형식으로 변환

        //Repository에서 DB조회 후 페이징 처리된 결과를 DTO로 변환해서 담음, 페이지로 나눈 데이터 + 페이징 정보
        Page<PostSummaryResDto> postPageDto = searchService.searchPosts(keyword, categoryEnum, pageable);

        //PaginationDto 생성(페이지화 된 정보(postPageDto)를 가져온다)
        PaginationDto paginationDto = new PaginationDto(
            postPageDto.getNumber() + 1, //0부터 시작해서 +1
            postPageDto.getTotalPages(), //총 페이지수
            postPageDto.getTotalElements(), //총 데이터 항목 수
            postPageDto.getSize() //해당 페이지의 보여지는 항목 수
        );
        //게시글 리스트와 페이지네이션 정보를 합친다
        PostPageResDto postPageResponseDto = new PostPageResDto(postPageDto.getContent(), paginationDto);
        return Response.success(SuccessStatus.POST_SUCCESS, postPageResponseDto);
    }
}
