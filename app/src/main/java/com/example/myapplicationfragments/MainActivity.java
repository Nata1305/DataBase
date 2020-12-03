package com.example.myapplicationfragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserList userList= UserList.get();//

        FragmentManager fragmentManager=getSupportFragmentManager();//возвращает элемент типа FragmentManager
        Fragment fragment=new UserListFragment();//создаем экземпляр класса ListFragment
        fragmentManager.beginTransaction().add(R.id.fragmentContainer,fragment).commit();//помещает элементы на экран-в FragmentContainer кладет сам фрагмент, commit фиксирует на экране

    }
}