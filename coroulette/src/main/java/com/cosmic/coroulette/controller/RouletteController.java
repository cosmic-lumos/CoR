package com.cosmic.coroulette.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.cosmic.coroulette.dao.HelloMessage;
import com.cosmic.coroulette.dao.Result;
import com.cosmic.coroulette.dao.Room;
import com.cosmic.coroulette.dao.RoomRegister;
import com.cosmic.coroulette.service.RoomManager;

@Controller
public class RouletteController {
    static Map<String, Room> rooms = new HashMap<>();

    private String randomName(int size){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<size;i++){
            sb.append((char)('A'+random.nextInt(26)));
        }
        return sb.toString();
    }

    @MessageMapping("/register")
    @SendTo("/topic/register")
    public Room roomRegister(RoomRegister registerForm) throws Exception{
        String randoms = randomName(5);
        Room newRoom = new Room(randoms, registerForm.getName());
        rooms.put(randoms, newRoom);
        
        return newRoom;
    }

    @MessageMapping("/enter")
    @SendTo("/topic/room")
    public Room roomEntered(@Header("simpSessionId") String sessionId) throws Exception{

        return RoomManager.getInstance();
    }

    @MessageMapping("/addCategory")
    @SendTo("/topic/room")
    public Room addCategories(HelloMessage category) throws Exception{
        RoomManager.getInstance().addCategory(category.getName());
        
        return RoomManager.getInstance();
    }

    @MessageMapping("/removeCategory")
    @SendTo("/topic/room")
    public Room removeCategories(HelloMessage category) throws Exception{
        RoomManager.getInstance().removeCategory(category.getName());
        
        return RoomManager.getInstance();
    }

    @MessageMapping("/selectNumber")
    @SendTo("/topic/room/result")
    public Result selectNumber() throws Exception{
        Random random = new Random();
        
        return new Result(random.nextInt(RoomManager.getInstance().getCategories().size()));
    }
}
