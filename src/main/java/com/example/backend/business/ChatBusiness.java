package com.example.backend.business;

import com.example.backend.exception.BaseException;
import com.example.backend.exception.ChatException;
import com.example.backend.model.ChatMessage;
import com.example.backend.model.ChatMessageReguest;
import com.example.backend.util.SecurityUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatBusiness {

    //class in spring to send message  past protocol socket
    private final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageReguest reguest) throws BaseException {
        //set this chat from who
        // before send chat need to login
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw ChatException.accessDenied();
        }

        //TODO: validate message

        final String destination = "chat"; //send to
        ChatMessage payload = new ChatMessage();
        payload.setFrom(opt.get()); //from
        payload.setMessage(reguest.getMessage()); //what message
        template.convertAndSend(destination, payload);
    }
}
