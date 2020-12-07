package com.example.myapplicationfragments.PackageDataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.myapplicationfragments.PackageDataBase.UserDbSchema.*;

public class UserBaseHelper extends SQLiteOpenHelper {
    //класс определяет имя и версию БД
    private static final int VERSION=1;
    private static final String DATABASE_NAME="userBase.db";
    public UserBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    // метод создает таблицу в БД если БД не существует
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ UserTable.NAME+" ("+
                "id integer primary key autoincrement,"+
                UserTable.Cols.UUID+"," +
                UserTable.Cols.USERNAME+","+
                UserTable.Cols.USERLASTNAME+")");
    }

    @Override
    //метод вызыаается если БД сущесвует,обновление БД
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
