package com.hfad.hotelapp.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Hotel{
    private int image;
    private int image2;
    private String name;
    private String location;
    private int rating;
    private String roomSize;
    private String distance;
    private ArrayList<Room> roomtype=new ArrayList<Room>();
    private ArrayList<String> facilities=new ArrayList<String>();

    public Hotel(int image,int image2, String name, String location, int rating, String roomSize,
                 String distance, String[] type,int[] price, String[] fac) {
        this.image = image;
        this.image2 = image2;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.roomSize = roomSize;
        this.distance = distance;

        for(int i=0;i<type.length;i++){
            roomtype.add(new Room(type[i],price[i]));
        }
//        for(int i=0;i<fac.length;i++){
//            facilities.add(fac[i]);
//        }
        facilities.addAll(Arrays.asList(fac));
        //Log.d("ooo","price= " + price[0]+" " + price[1]+" " + price[2]);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {this.image = image;}

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        String s=""+rating;
        return s;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public ArrayList<Room> getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(ArrayList<Room> roomtype) {
        this.roomtype = roomtype;
    }

    public ArrayList<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<String> facilities) {
        this.facilities = facilities;
    }

    public String getTypeFromRoom(int pos){
        Log.d("ooo","pos="+pos);
        return roomtype.get(pos).getType();
    }

    public int getPrizeFromRoom(int pos){
        //Log.d("qqq","roomtype.get(pos).getPrice()=" + roomtype.get(pos));
        return roomtype.get(pos).getPrice();
        //return 599;
    }
}

