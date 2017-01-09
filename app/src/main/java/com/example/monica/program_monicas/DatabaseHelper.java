package com.example.monica.program_monicas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by monica on 1/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="mahasiswa.db";
    private static final String TABLE_NAME ="tabelmhs";
    private static final String UID="_id";
    private static final String NAME="Nama";
    private static final int DATABASE_VERSION=1;
    DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tablemhs(nim integer primary key, nama text null, alamat text null, jk text null, jurusan text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata (nim, nama, alamat, jk, jurusan) VALUES ('100101', 'Andre', 'Gading Serpong', 'Laki-laki','Teknik Informatika');";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion)
    {
        //db.execSQL("DROP TABLE IF EXISTS tabelmhs");
        //onCreate(db);
    }


}
