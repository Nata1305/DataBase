package com.example.myapplicationfragments;

import java.util.ArrayList;
import java.util.List;

// Синглетный класс (может быть создан только один объект)
public class UserList {
    private static UserList userList;
    private List users=new ArrayList();
    public static UserList get(){
        if(userList == null){
            userList = new UserList();
        }
        return userList;
    }
    private UserList(){

    }
    public List getUsers(){
        return users;
    }
    public void addUser (User user){
        users.add(user);

    }
}
