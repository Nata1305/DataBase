package com.example.myapplicationfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;//создаем переменную, в которой будет находиться RecyclerView
    private UserAdapter userAdapter;//создали переменную для адаптера
    //метод создает компонент View фрагмента из xml разметки
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){//Фрагмент создает элемент для отображения
        // и отдает его активности.
        View view=inflater.inflate(R.layout.fragment_user_list,viewGroup,false);//создаем вью для отображения фрагмента со сриском в главной активности
    userRecyclerView=view.findViewById(R.id.userRecyclerView);//находим по id фрагмент со списком
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//чтобы отобразить список на фрагменте, LinearLayoutManager
                                                                                  // - для размещения элементов горизонтально списком
                                                                                  // для привязки к главной активности
        UserList userList=UserList.get();//получаем список с пользователями
        List<User> users=userList.getUsers();
        userAdapter=new UserAdapter(users);// помещаем в адаптер список
        userRecyclerView.setAdapter(userAdapter);// передаем список пользователей из адаптера в RecycleView
        return view;
    }
    private class UserHolder extends RecyclerView.ViewHolder{//класс, которым пользуется Recycler для отображения элементов
    private TextView userItem;// создаем приватную переменную для отображения имени&фамилии конкретного элемента списка
       public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){// конструктор для создания компонента вью для размещения элемента в списке
           super(inflater.inflate(R.layout.list_item_user, viewGroup,false));// раздувает элемент списка
           userItem=itemView.findViewById(R.id.userItem);//

       }
       public void bind(User user){//связывает элемент списка с view
           String userName="имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n-------";// склеили строку имя и фамилию конкретного пользователя
           userItem.setText(userName);
       }
    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{// создаем второй вспомогатеьный класс для Recycler,
                                                                       // наследуется от Recycle и указавыем какой
                                                                       // Holder используется для этого Adapter
    private List<User> users;//создаем приватную переменную список пользователей
    public UserAdapter(List<User> users){// создаем конструктор, по которому в адаптер будет подгружаться список пользователей
        this.users=users;
    }


        @Override  // имплементируем обязательные методы в рамках метода RecyclerView.Adapter<UserHolder>
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {// RecyclerView обращается к этому методу, чтобы создать следующий элемент списка и показать его в фрагменте
        LayoutInflater inflater=LayoutInflater.from(getActivity());//
        return new UserHolder(inflater,viewGroup);//возвращает объект класса UserHolder
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {// связвает элемент списка с элементом отображения
        User user=users.get(position);//получили элемент из списка и сохранили
            userHolder.bind(user);//метод

        }

        @Override
        public int getItemCount() {// возвращает количество элементов списка
            return 0;
        }
    }

}
