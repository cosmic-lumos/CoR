package com.cosmic.coroulette.dao;

import java.util.ArrayList;
import java.util.List;

public class Room{
    private String roomId;
    private List<User> users;
    private List<Category> categories;

    public Room(){
        roomId = "room";
        users = new ArrayList<>();
        categories = new ArrayList<>();
    }

    public void addUser(String id){
        users.add(new User(id));
    }

    public void addCategory(String name){
        categories.add(new Category(name));
    }

    public String[] getCategories(){
        return categories.stream()
                        .map(category -> category.getName())
                        .toArray(String[]::new);
    }
    
}