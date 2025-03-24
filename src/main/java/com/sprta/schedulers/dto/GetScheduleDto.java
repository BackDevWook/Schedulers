package com.sprta.schedulers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetScheduleDto {
    // 작성자, 제목, 내용, 작성일, 수정일 보여주기
    // 비밀번호는 받아서 확인하는 용도
    private String whoName;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
