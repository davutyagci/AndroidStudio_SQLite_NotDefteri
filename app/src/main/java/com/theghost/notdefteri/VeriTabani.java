package com.theghost.notdefteri;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class VeriTabani extends SQLiteOpenHelper {

    public static final String VERITABANI_ADI = "Not_Defteri.db";
    public static final int VERITABANI_VERSION = 1;
    public static final String TABLO_ADI = "Notlar";

    public static final String STN_ID = "ID";
    public static final String STN_NOT = "NOTLAR";
    public static final String STN_TARIH = "TARIH";

    public VeriTabani(Context context) {
        super(context, VERITABANI_ADI, null, VERITABANI_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table if not exists " + TABLO_ADI + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOTLAR TEXT, TARIH TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_ADI);
        onCreate(db);
    }

    public boolean NotKayit(String notkaydet, String tarihkaydet) {
        SQLiteDatabase dbYaz = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STN_NOT, notkaydet);
        cv.put(STN_TARIH, tarihkaydet);
        long sonuc = dbYaz.insert(TABLO_ADI, null, cv);
        if(sonuc == -1) { return false; }
        else { return true; }
    }

    public ArrayList<String> NotIdListele() {
        SQLiteDatabase dbOku = this.getReadableDatabase();
        ArrayList<String> al = new ArrayList<>();
        Cursor c = dbOku.rawQuery("Select * From " + TABLO_ADI, null);
        while (c.moveToNext()) {
            al.add(c.getString(0));
        }
        return al;
    }

    public ArrayList<String> NotListele() {
        SQLiteDatabase dbOku = this.getReadableDatabase();
        ArrayList<String> al = new ArrayList<>();
        Cursor c = dbOku.rawQuery("Select * From " + TABLO_ADI, null);
        while (c.moveToNext()) {
            al.add(c.getString(1));
        }
        return al;
    }

    public ArrayList<String> TarihListele() {
        SQLiteDatabase dbOku = this.getReadableDatabase();
        ArrayList<String> al = new ArrayList<>();
        Cursor c = dbOku.rawQuery("Select * From " + TABLO_ADI, null);
        while (c.moveToNext()) {
            al.add(c.getString(2));
        }
        return al;
    }

    public SQLiteDatabase NotCek() {
        SQLiteDatabase dbOku = this.getReadableDatabase();
        return dbOku;
    }

    public Integer NotSil(String idsil) {
        SQLiteDatabase dbYaz = this.getWritableDatabase();
        return dbYaz.delete(TABLO_ADI, "ID = ?", new String[] {idsil});
    }

    public boolean NotGuncelle (String idguncelle, String notguncelle, String tarihguncelle) {
        SQLiteDatabase dbYaz = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STN_ID, idguncelle);
        cv.put(STN_NOT, notguncelle);
        cv.put(STN_TARIH, tarihguncelle);
        dbYaz.update(TABLO_ADI, cv, "ID = ?", new String[] {idguncelle});
        return true;
    }
}
