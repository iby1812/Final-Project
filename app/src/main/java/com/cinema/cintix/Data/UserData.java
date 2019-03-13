package com.cinema.cintix.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserData {
    String id;
    String name;
    ArrayList<String> friends;

    public UserData(){
        name="";
        id="";
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(ArrayList<String> f) {
        friends.addAll(f);
    }
}
