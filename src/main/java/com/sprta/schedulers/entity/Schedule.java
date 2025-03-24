package com.sprta.schedulers.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String whoName; // 글자 제한 수 : 25
    private String title; // 글자 제한 수 : 25
    private String content; // 글자 제한 수 : 500
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String password; // 글자 제한 수 : 25
}
