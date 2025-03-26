package com.sprta.schedulers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetScheduleDto {
    // 작성자, 제목, 내용, 작성일, 수정일 보여주기
    // id는 전체조회를 위해 사용
    private Long id;
    private String whoName;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
