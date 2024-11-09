package com.example.ancg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Ropas.sb";
    private static final String Table_Name ="clothes";
    private static final String Col_1="ID";
    private static final String Col_2="clothes";

    public Database(Context context){
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, CLOTHES TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    public boolean InsertClothes(String clothes){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, clothes);
        long result = db.insert(Table_Name, null, contentValues);
        return result != -1;
    }
    public ArrayList<String> getAllClothes(){
        ArrayList<String> clothes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Name, null);
        if(res.moveToFirst()){
            do{
                clothes.add(res.getString(1));
            }while (res.moveToNext());
        }
        return clothes;
    }

    public void deleteClothes(String clothes){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name, "CLOTHES = ?", new String[{clothes}]);
    }
    public void updateClothes(String oldClothes, String newClothes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,newClothes);
            db.update(Table_Name, contentValues, "CLOTHES = ?",new String[]{oldClothes});
    }

}