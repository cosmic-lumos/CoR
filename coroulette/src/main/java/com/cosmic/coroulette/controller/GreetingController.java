package com.cosmic.coroulette.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.cosmic.coroulette.dao.HelloMessage;
import com.cosmic.coroulette.dao.Greeting;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100);
        return new Greeting("카테고리에 " + HtmlUtils.htmlEscape(message.getName()) + "이 추가되었습니다.\n");
    }

    @MessageMapping("/person")
    // @SendTo("/topic/greetings")
    public void register(@Header("simpSessionId") String sessionId) throws Exception{
        // return new Greeting("Hello, " + HtmlUtils.htmlEscape(sessionId) + "!");
    }
}