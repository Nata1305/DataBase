package com.example.myapplicationfragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationfragments.R;
import com.example.myapplicationfragments.UserFragment;
import com.example.myapplicationfragments.UserListFragment;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new UserListFragment();
        // R.id.fragmentContainer - это FrameLayout из файла activity_main.xml
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }



     @Override
    public void onBackPressed() {
         //super.onBackPressed();
         FragmentManager fragmentManager=getSupportFragmentManager();
         Fragment fragment=new UserListFragment();
         fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
         Toast.makeText(MainActivity.this,"Сейчас будет выход",Toast.LENGTH_SHORT).show();
  }
    public static void changeFragment(View view, com.example.myapplicationfragments.User user){
        // Получаем хостинговую активность (в нашем случае MainActivity)
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Создаём менеджер фрагментов
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // создаём фрагмент
        Fragment fragment = new UserFragment();
        // Создаём bundle (это как коллекция)
        Bundle bundle = new Bundle();
        // Записываем user в bundle для передачи в фрагмент
        bundle.putSerializable("user", user);
        // Кладём Bundle в фрагмент
        fragment.setArguments(bundle);
        //Заменяем фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}