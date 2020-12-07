package com.example.myapplicationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    private Button add_user_btn_in_user_list;

    // Метод создаёт компонент View фрагмента из XML разментки
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_list,viewGroup,false);
        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        add_user_btn_in_user_list=view.findViewById(R.id.add_user_btn_in_user_list);

        com.example.myapplicationfragments.UserList userList = com.example.myapplicationfragments.UserList.get(this.getActivity());
        List<com.example.myapplicationfragments.User> users = userList.getUsers();
         userAdapter = new UserAdapter(users);
        userRecyclerView.setAdapter(userAdapter);

        add_user_btn_in_user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newUser=new AddNewUserFragment();
                FragmentTransaction tr=getFragmentManager().beginTransaction();
                tr.replace(R.id.fragmentContainer,newUser);
                tr.commit();
            }
        });
        return view;
    }

    // Класс UserHolder формирует элементы списка
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView userItemTextView;
        private com.example.myapplicationfragments.User itemUser;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user,viewGroup,false));
            // itemView - это элемент списка
            userItemTextView = itemView.findViewById(R.id.userItem);
            itemView.setOnClickListener(this);
        }
        public void bind(com.example.myapplicationfragments.User user){
            itemUser = user;
            String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n---------";
            userItemTextView.setText(userName); // Устанавливаем текст элемента списка
        }

        @Override
        public void onClick(View view) {
            com.example.myapplicationfragments.MainActivity.changeFragment(view, itemUser);
        }
    }

    // Класс UserAdapter отдаёт элементы в RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<com.example.myapplicationfragments.User> users;
        public UserAdapter(List<com.example.myapplicationfragments.User> users){
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            com.example.myapplicationfragments.User user = users.get(position);
            userHolder.bind(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

}