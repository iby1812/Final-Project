package com.cinema.cintix.data;
import java.net.URL;
import java.util.ArrayList;

public class UserData {
    String id;
    String name;
    ArrayList<String> friends;
    URL image;

    public UserData(){
        name="";
        id="";
        image=null;
    }
    public String getName() {
        return name;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
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
