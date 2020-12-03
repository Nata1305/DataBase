package com.example.myapplicationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserFragment extends Fragment {//наследуемся от класса Fragment
  private  User user;
  private TextView userName_userLastName_View;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        user=new User();
        user.setUserName("Ivan");
        user.setUserLastName("Ivanov");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){//Фрагмент создает элемент для отображения
                                                                                                     // и отдает его активности.
        View view=inflater.inflate(R.layout.fragment_user,container, false); //Inflater- разувает родительскую активность фрагментом , false=привязывает к родит. макету container
              userName_userLastName_View=view.findViewById(R.id.userName_userLastName_View);//доступ к элементу конкретного пользователя на фрагменте
        String userName="имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();// склеили строку имя и фамилию конкретного пользователя
        userName_userLastName_View.setText(userName);//вывели на фрагмент элемент имени и фамилии пользователя

        return view;
    }

  }
