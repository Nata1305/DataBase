package com.example.myapplicationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddNewUserFragment extends Fragment {
    private EditText input_new_user_name;
    private EditText input_new_user_lastName;
    private Button add_new_user_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_user,container,false);
        add_new_user_btn=view.findViewById(R.id.add_new_user_btn);
        input_new_user_name=view.findViewById(R.id.input_new_user_name);
        input_new_user_lastName=view.findViewById(R.id.input_new_user_lastName);


        add_new_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name=input_new_user_name.getText().toString();
                String user_Lastname=input_new_user_lastName.getText().toString();
                User user=new User();
                user.setUserName(user_name);
                user.setUserLastName(user_Lastname);
                UserList userList = UserList.get(getActivity());
                userList.addUser(user);

                Fragment list=new UserListFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,list);
                transaction.commit();
            }
        });

return view;
    }
}
