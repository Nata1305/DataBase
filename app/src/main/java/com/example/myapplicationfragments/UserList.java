package com.example.myapplicationfragments;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.myapplicationfragments.PackageDataBase.UserBaseHelper;
import com.example.myapplicationfragments.PackageDataBase.UserDbSchema;
import java.util.ArrayList;
import java.util.List;

// Синглетный класс (может быть создан только один объект)
public class UserList {
    private static UserList userList;
    private Context context;
    private SQLiteDatabase database;
    private List users;
    public static UserList get(Context context){
        if(userList == null){
            userList = new UserList(context);
        }
        return userList;
    }

    private UserList(Context context){
        this.context=context.getApplicationContext();
        database=new UserBaseHelper(context).getWritableDatabase();

    }
    public List getUsers(){
        users=new ArrayList();

        UserCursorWrapper cursor=queryUsers(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
          return users;

    }
    public void addUser (User user){
        //записываем в базу данных значения пользователя
        ContentValues values=getContentValues(user);
        //У метода insert() три аргумента. В 1-м указывается имя таблицы,в 3-м указывается объект ContentValues, созданный ранее. 2-й аргумент- для указания колонки.
        // SQL не позволяет вставлять пустую запись, и если будет использоваться пустой ContentValue, то во втором аргументе null во избежание ошибки.
        database.insert(UserDbSchema.UserTable.NAME,null,values);

       users.add(user);// ПРОВЕРИТЬ ЧТОБЫ НЕ ДУБЛИРОВАЛИСЬ

    }
    private static ContentValues getContentValues(User user){
        ContentValues values=new ContentValues();
        //вспомогательный объект для хранения ключ (название столбца) и значение (параметр пользователя-UUID,name or lastName
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.USERLASTNAME, user.getUserLastName());
        return values;

    }


    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        //получает значения из базы банных
        Cursor cursor= database.query(
                UserDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs, null,null,null);
        return new UserCursorWrapper(cursor);

    }
    public void updateUser(User user){
        String uuidString=user.getUuid().toString();
        ContentValues values=getContentValues(user);

        database.update(UserDbSchema.UserTable.NAME,values, UserDbSchema.UserTable.Cols.UUID+"=?",
                new String[]{uuidString});
    }
    public void delete (User user){
        //для удаления данных из БД
        String uuidString=user.getUuid().toString();
        ContentValues values=getContentValues(user);
        database.delete(UserDbSchema.UserTable.NAME,UserDbSchema.UserTable.Cols.UUID+"=?",
                new String[]{uuidString});

    }

}
