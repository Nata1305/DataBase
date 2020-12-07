package com.example.myapplicationfragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {
    private com.example.myapplicationfragments.User user;
    private TextView userName_userLastname_View;
    private EditText editNewName;
    private EditText editNewLastName;
    private Button updateBtn;
    private Button deleteUserBtn;
    private UserList userList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");  // Принимаем объект user
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        userList =userList.get(getActivity());

        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        editNewName=view.findViewById(R.id.editNewName);
        editNewLastName=view.findViewById(R.id.editNewLastName);
        updateBtn=view.findViewById(R.id.updateBtn);
        deleteUserBtn=view.findViewById(R.id.deleteUserBtn);

        String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newUserName=editNewName.getText().toString();
                String newUserLastName=editNewLastName.getText().toString();
                if (newUserName.trim().isEmpty()&&newUserLastName.trim().isEmpty())
                    Toast.makeText(getActivity(),"Вы не внесли изменения.Попробуйте еще раз",Toast.LENGTH_SHORT).show();
                else{
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastName);
                userList.updateUser(user);
                Fragment list=new UserListFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,list);
                transaction.commit();
                Toast.makeText(getActivity(),"Изменения внесены",Toast.LENGTH_SHORT).show();}

            }
        });

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.delete(user);
                Fragment list=new UserListFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,list);
                transaction.commit();
                Toast.makeText(getActivity(),"Пользователь успешно удален",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}