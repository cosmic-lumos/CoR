package com.cosmic.coroulette.dao;

import java.util.ArrayList;
import java.util.List;

public class Room{
    private String id;
    private String name;
    private List<User> users;
    private List<Category> categories;

    public Room(String id, String name){
        this.id = id;
        this.name = name;
        users = new ArrayList<>();
        categories = new ArrayList<>();
    }
    

    public void addUser(String id){
        users.add(new User(id));
    }

    public void addCategory(String name){
        categories.add(new Category(name));
    }

    public String[] getStringCategories(){
        return categories.stream()
                        .map(category -> category.getName())
                        .toArray(String[]::new);
    }

    public String[] getStringUsers(){
        return users.stream()
                    .map(user -> user.getName())
                    .toArray(String[]::new);
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<Category> getCategories() {
        return categories;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
}