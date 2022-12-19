package com.likelion.exam.app_2022_12_19.controller;

import com.likelion.exam.app_2022_12_19.domain.ChatMessage;
import com.likelion.exam.app_2022_12_19.domain.dto.ResponseData;
import com.likelion.exam.app_2022_12_19.domain.dto.WriteMessageRequest;
import com.likelion.exam.app_2022_12_19.domain.dto.WriteMessageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final List<ChatMessage> chatMessages = new ArrayList<>();


    @PostMapping("/writeMessage")
    @ResponseBody
    public ResponseData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest request) {

        ChatMessage message = new ChatMessage(request.getAuthorName(), request.getContent());

        chatMessages.add(message);

        return new ResponseData<>("S-1", "메시지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
        new WriteMessageResponse(message.getId());
    }

    @GetMapping("/messages")
    @ResponseBody
    public ResponseData<List<ChatMessage>> messages() {
        return new ResponseData<>("S-1", "성공", chatMessages);
    }
}