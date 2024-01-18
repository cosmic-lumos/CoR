package com.cosmic.coroulette.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Room{
    private String id;
    private String name;
    private Set<User> users;
    private Map<Category, Integer> categories;

    public Room(String id, String name){
        this.id = id;
        this.name = name;
        users = new HashSet<>();
        categories = new HashMap<>();
        categories.put(new Category("짬뽕"), 1);
        categories.put(new Category("햄버거"), 1);
        categories.put(new Category("짜장면"), 1);
    }
    

    public void addUser(String id){
        users.add(new User(id));
    }

    public void removeUser(String id){
        users.removeIf(user -> user.getsessionId().equals(id));
    }

    public void addCategory(String name){
        Category key = new Category(name);
        if(categories.containsKey(key)){
            categories.put(key, categories.get(key)+1);
            return;
        }
        categories.put(key, 1);
    }

    public void removeCategory(String name){
        Category key = new Category(name);
        if(!categories.containsKey(key)){
            return;
        }
        if(categories.get(key) == 1){
            categories.remove(key);
            return;
        }
        categories.put(key, categories.get(key)-1);
    }

    // 필요없는 기능
    // public String[] getStringCategories(){
    //     return categories.stream()
    //                     .map(category -> category.getName())
    //                     .toArray(String[]::new);
    // }

    // 필요없는 기능
    // public String[] getStringUsers(){
    //     return users.stream()
    //                 .map(user -> user.getName())
    //                 .toArray(String[]::new);
    // }

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


    public Map<Category, Integer> getCategories() {
        return categories;
    }


    public void setCategories(Map<Category, Integer> categories) {
        this.categories = categories;
    }
    
}