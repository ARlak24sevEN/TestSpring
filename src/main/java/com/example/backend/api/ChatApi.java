package com.example.backend.api;


import com.example.backend.business.ChatBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.ChatMessageReguest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {

    private final ChatBusiness business;

    public ChatApi(ChatBusiness business) {
        this.business = business;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody ChatMessageReguest reguest) throws BaseException    {
        business.post(reguest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
