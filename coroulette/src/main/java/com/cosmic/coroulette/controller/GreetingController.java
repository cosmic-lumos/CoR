package com.cosmic.coroulette.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.cosmic.coroulette.dao.HelloMessage;
import com.cosmic.coroulette.dao.Room;
import com.cosmic.coroulette.dao.Greeting;

@Controller
public class GreetingController {
    static Room room = new Room();

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100);
        room.addCategory(message.getName());
        return new Greeting("카테고리에 " + HtmlUtils.htmlEscape(message.getName()) + "이 추가되었습니다.\n" + "현재 카테고리는 " + String.join(", ", room.getCategories()) + "입니다.");
    }

    @MessageMapping("/register")
    // @SendTo("/topic/greetings")
    public void register(@Header("simpSessionId") String sessionId) throws Exception{
        room.addUser(sessionId);
        // return new Greeting("Hello, " + HtmlUtils.htmlEscape(sessionId) + "!");
    }
}