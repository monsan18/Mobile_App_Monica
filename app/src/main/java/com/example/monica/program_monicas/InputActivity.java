package com.example.monica.program_monicas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button submit;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dbHelper = new DatabaseHelper(this);
        text1 = (EditText) findViewById(R.id.nim);
        text2 = (EditText) findViewById(R.id.nama);
        text3 = (EditText) findViewById(R.id.alamat);
        text4 = (EditText) findViewById(R.id.jk);
        text5 = (EditText) findViewById(R.id.jurusan);
        submit = (Button) findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into tablemhs(nim, nama, alamat, jk, jurusan) values('" +
                        text1.getText().toString()+"','"+
                        text2.getText().toString() +"','" +
                        text3.getText().toString()+"','"+
                        text4.getText().toString() +"','" +
                        text5.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                ViewActivity.va.RefreshList();
            }
        });

    }
}
