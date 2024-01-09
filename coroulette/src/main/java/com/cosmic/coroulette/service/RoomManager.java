package com.cosmic.coroulette.service;

import com.cosmic.coroulette.dao.Room;

public class RoomManager {
    private RoomManager(){}

    public static Room getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final Room INSTANCE = new Room("null", "null");
    }
}
