package com.example.thuchanhck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "db.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE NhaSanXuat(MaSX integer primary key, Ten text, DiaChi text)");
        sqLiteDatabase.execSQL("CREATE TABLE SanPham(MaSo integer primary key, Ten text, DVT text, DonGia text, MaSX integer, FOREIGN KEY(MaSX) REFERENCES NhaSanXuat(MaSX))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NhaSanXuat");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SanPham");
        onCreate(sqLiteDatabase);
    }

    public int insertNhaSanXuat(NSX nsx){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSX",nsx.getMaSX());
        contentValues.put("Ten",nsx.getTen());
        contentValues.put("DiaChi",nsx.getDiaChi());

        int result = (int) database.insert("NhaSanXuat",null,contentValues);
        database.close();
        return result;
    }
    public int insertSP(SP sp){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSo",sp.getMaSo());
        contentValues.put("Ten",sp.getTen());
        contentValues.put("DVT",sp.getDVT());
        contentValues.put("DonGia",sp.getDonGia());
        contentValues.put("MaSX",sp.getMaSX());

        int result = (int) database.insert("SanPham",null,contentValues);
        database.close();
        return result;
    }

    public ArrayList<NSX> getAllNSX(){
        ArrayList<NSX> nsxes = new ArrayList<>();
        SQLiteDatabase database =getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NhaSanXuat",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            nsxes.add(new NSX(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return nsxes;
    }
    public ArrayList<SP> getAllSP(){
        ArrayList<SP> spes = new ArrayList<>();
        SQLiteDatabase database =getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM SanPham",null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            spes.add(new SP(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return spes;
    }

    public NSX getAllNSXByID(int id){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NhaSanXuat WHERE MaSX= "+id+" ",null);
        if(cursor!=null)
            cursor.moveToFirst();

        NSX nsx = new NSX(cursor.getInt(0),cursor.getString(1),cursor.getString(2));

        cursor.close();
        database.close();
        return nsx;
    }
    public SP getAllSPByID(int id){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM SanPham WHERE MaSo= "+id+" ",null);
        if(cursor!=null)
            cursor.moveToFirst();

        SP sp = new SP(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));

        cursor.close();
        database.close();
        return sp;
    }

    public boolean deleteNSX(int id){
        SQLiteDatabase database = getWritableDatabase();
        if(database.delete("NhaSanXuat","MaSX" + "=?",
                new String[] {String.valueOf(id)}) > 0){
            database.close();
        };
        return true;
    }
    public boolean deleteSP(int id){
        SQLiteDatabase database = getWritableDatabase();
        if(database.delete("SanPham","MaSo" + "=?",
                new String[] {String.valueOf(id)}) > 0){
            database.close();
        };
        return true;
    }
    public boolean updateNSX(int MaSX, String Ten, String DiaChi){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("MaSX",MaSX);
        contentValues.put("Ten",Ten);
        contentValues.put("DiaChi",DiaChi);

        database.update("NhaSanXuat",contentValues,"MaSX ="+MaSX,null);
        return true;
    }
    public boolean updateSP(int MaSo, String Ten, String DVT,String DonGia, int MaSX){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("MaSo",MaSo);
        contentValues.put("Ten",Ten);
        contentValues.put("DVT",DVT);
        contentValues.put("DonGia",DonGia);
        contentValues.put("MaSX",MaSX);

        database.update("SanPham",contentValues,"MaSo ="+MaSo,null);
        return true;
    }

    // truy vấn không trả về kết quá Create,insert,update,delete,...
    public void Querydata(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // truy vấn trả về kết quả: Select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql,null);
    }
}
