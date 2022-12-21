package com.likelion.exam.app_2022_12_19.domain.dto;

import com.likelion.exam.app_2022_12_19.util.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessagesResponse {
    private List<ChatMessage> messages;
    private long count;
}