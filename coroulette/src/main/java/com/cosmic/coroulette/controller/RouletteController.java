package com.cosmic.coroulette.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.cosmic.coroulette.dao.Room;
import com.cosmic.coroulette.dao.RoomRegister;

@Controller
public class RouletteController {
    static Map<String, Room> rooms = new HashMap<>();

    private String randomName(int size){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<size;i++){
            sb.append('A'+random.nextInt(26));
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
}
