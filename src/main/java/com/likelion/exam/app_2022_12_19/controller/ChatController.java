package com.likelion.exam.app_2022_12_19.controller;

import com.likelion.exam.app_2022_12_19.util.ChatMessage;
import com.likelion.exam.app_2022_12_19.domain.ResponseData;
import com.likelion.exam.app_2022_12_19.domain.dto.*;
import com.likelion.exam.app_2022_12_19.util.SSEEmitters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final SSEEmitters sseEmitters;
    private final List<ChatMessage> chatMessages = new ArrayList<>();

    @GetMapping("/room")
    public String showRoom() {
        return "chat/room";
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public ResponseData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest request) {

        ChatMessage message = new ChatMessage(request.getAuthorName(), request.getContent());

        chatMessages.add(message);

        sseEmitters.notify("chat__messageAdded");

        return new ResponseData<>("S-1", "메시지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public ResponseData<MessagesResponse> messages(MessagesRequest request) {
        List<ChatMessage> messages = chatMessages;

        // 번호가 입력되었다면
        if (request.getFromId() != null) {
            // 해당 번호의 채팅메세지가 전체 리스트에서의 배열인덱스 번호를 구한다.
            // 없다면 -1
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == request.getFromId())
                    .findFirst()
                    .orElse(-1);

//            int foundIndex = -1;
//            for (int i = 0; i < messages.size(); i++) {
//                if (messages.get(i).getId() == request.getFromId()) {
//                    foundIndex = i;
//                    break;
//                }
//            }

            if (index != -1) {
                // 만약에 index 가 있다면, 0번 부터 index 번 까지 제거한 리스트를 만든다.
                messages = messages.subList(index + 1, messages.size());
            }
        }

        return new ResponseData<>("S-1", "성공", new MessagesResponse(messages, messages.size()));
    }
}