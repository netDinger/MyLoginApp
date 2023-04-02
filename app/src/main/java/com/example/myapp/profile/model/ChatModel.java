package com.example.myapp.profile.model;

public class ChatModel {

    private String name;
    private String lastMsg;

    public ChatModel(String name, String lastMsg){
        this.name = name;
        this.lastMsg = lastMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

}



