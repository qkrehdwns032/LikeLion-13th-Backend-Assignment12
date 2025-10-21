package com.likelion.search_likelion.post.application;

import com.likelion.search_likelion.exception.CustomException;
import com.likelion.search_likelion.exception.Response;
import com.likelion.search_likelion.exception.status.ErrorStatus;
import com.likelion.search_likelion.exception.status.SuccessStatus;
import com.likelion.search_likelion.post.api.dto.request.PostSaveReqDto;
import com.likelion.search_likelion.post.api.dto.response.PostInfoResDto;
import com.likelion.search_likelion.post.domain.Post;
import com.likelion.search_likelion.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    //공고 저장
    @Transactional
    public Response<Long> postSave(PostSaveReqDto postSaveRequestDto) {
        if(postSaveRequestDto.category() == null){
            throw new CustomException(ErrorStatus.POST_CREATE_FAILED);
        }


        Post post = Post.builder()
            .title(postSaveRequestDto.title())
            .content(postSaveRequestDto.content())
            .createAt(LocalDate.now())
            .category(postSaveRequestDto.category())
            .build();
        postRepository.save(post);
        return Response.success(SuccessStatus.POST_CREATED, post.getPostId());
    }

    //PostId로 공고 상세 조회
    public PostInfoResDto getPostId(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorStatus.POST_NOT_FOUND));

        return PostInfoResDto.from(post);
    }

    //공고 요약 전체 조회(페이지네이션)
    public Page<Post> getPostPage(Pageable pageable) {
        return postRepository.findAll(pageable);

    }
}
