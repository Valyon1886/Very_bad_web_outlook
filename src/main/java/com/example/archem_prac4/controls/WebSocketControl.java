package com.example.archem_prac4.controls;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.example.archem_prac4.components.WSMessage;

@Controller
public class WebSocketControl {
    @MessageMapping("/send")
    @SendTo("/")
    public WSMessage greeting(WSMessage message) throws InterruptedException {
        Thread.sleep(200);
        return message;
    }
}
