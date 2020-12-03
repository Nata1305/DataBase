package com.example.myapplicationfragments;

import java.util.ArrayList;
import java.util.List;

//синглетные классы (может быть создан только один объект(список))
public class UserList {
    private static UserList userList;  // создаем приватный класс для списка пользователей, для исключения создания двух одинаковых обектов класса(в данном случае списков)
    private List users;
    public static UserList get() {  //создаем публичный метод у класса, который будет возвращать userList
        if (userList == null) { // если списка не существует, то создается новый UserList со списком
            userList = new UserList();
        }
        return userList;// иначе, возвращает уже имеющийся список
    }
    private UserList() {
        users=new ArrayList();
        for (int i = 0; i <100 ; i++) {
            User user =new User();
            user.setUserName("Имя"+i);
            user.setUserName("Фамилия "+i);
            users.add(user);
            
        }

    }

    public List getUsers() {//отдает список пользователей
        return users;
    }
}