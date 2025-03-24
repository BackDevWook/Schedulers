package com.sprta.schedulers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostScheduleDto {
    // 작성자 이름, 제목, 내용, 비밀번호
    private String whoName;
    private String title;
    private String content;
    private String password;
}
