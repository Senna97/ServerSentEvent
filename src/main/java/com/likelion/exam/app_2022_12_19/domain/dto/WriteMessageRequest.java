package com.likelion.exam.app_2022_12_19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WriteMessageRequest {
    private String authorName;
    private String content;
}
