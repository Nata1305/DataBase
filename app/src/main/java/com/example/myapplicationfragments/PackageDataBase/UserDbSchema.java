package com.example.myapplicationfragments.PackageDataBase;

public class UserDbSchema {
    //класс для формирования таблицы с неизменяемыми названиями столбцов в базе данных
    public static class UserTable{
        public static final String NAME="users";
        public static final class Cols{
            public static final String UUID="uuid";
            public static final String USERNAME="username";
            public static final String USERLASTNAME="userlastname";

        }
    }
}
