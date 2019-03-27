package com.cinema.cintix.data;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class UserData implements Parcelable {
    String id;
    String name;
    ArrayList<String> friends;
    String image;

    public UserData(){
        name="";
        id="";
        image="";
    }

    protected UserData(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        friends = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeStringList(friends);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
