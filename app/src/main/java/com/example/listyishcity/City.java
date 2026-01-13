package com.example.listyishcity;

import androidx.annotation.NonNull;

public class City {
    private String name;
    public City(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public String toString(){
        return this.name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
