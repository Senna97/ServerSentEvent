package com.likelion.exam.app_2022_12_19.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseData<T> {
    private String resultCode;
    private String message;
    private T data;
}
