package com.cosmic.coroulette.dao;

import java.util.HashSet;
import java.util.Set;

public class Room{
    private String id;
    private String name;
    private Set<User> users;
    private Set<Category> categories;

    public Room(String id, String name){
        this.id = id;
        this.name = name;
        users = new HashSet<>();
        categories = new HashSet<>();
    }
    

    public void addUser(String id){
        users.add(new User(id));
    }

    public void removeUser(String id){
        users.removeIf(user -> user.getsessionId() == id);
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


    public Set<User> getUsers() {
        return users;
    }


    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public Set<Category> getCategories() {
        return categories;
    }


    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    
}