package com.example.myapp.profile.presenter;

import android.os.Parcelable;

public class ResponseMapper {

    User map(String json){
        //do json parsing
        return new User();
    }


    public class User{
        String id;
        String name;
        String avatar;

        public User(){}

        public User(String id, String name, String avatar) {
            this.id = id;
            this.name = name;
            this.avatar = avatar;
        }

        //alt+insert
        //ctrl+enter (on Mac)

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

}

