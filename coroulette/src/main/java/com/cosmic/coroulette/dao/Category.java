package com.cosmic.coroulette.dao;

import java.util.Objects;

public class Category {
    private String name;
    private int hashCode;

    public Category(String name){
        this.name = name;
        this.hashCode = Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Category that = (Category)obj;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
