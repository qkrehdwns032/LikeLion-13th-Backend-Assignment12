package com.likelion.search_likelion.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    // COMMON 4XX
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON400", "파라미터가 올바르지 않습니다."),
    INVALID_BODY(HttpStatus.BAD_REQUEST, "COMMON400", "요청 본문이 올바르지 않습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "찾을 수 없는 리소스입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "사용자를 찾을 수 없습니다"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "허용되지 않는 HTTP Method입니다."),



    //post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST404", "공고를 찾을 수 없습니다."),
    POST_ALREADY_DELETE(HttpStatus.BAD_REQUEST,"POST400", "이미 삭제된 게시글입니다."),
    POST_CREATE_FAILED(HttpStatus.BAD_REQUEST, "POST400", "공고 생성을 실패했습니다."),
    POST_UPDATE_FAILED(HttpStatus.BAD_REQUEST,"POST400", "공고 수정에 실패했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}