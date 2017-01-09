package com.example.monica.program_monicas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText editUsername;
    private EditText editPassword;
    String[] username, pass;
    private TextView txtStatus;
    //private TextView loginErrorMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();

        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        // loginErrorMsg = (TextView) findViewById(R.id.login_error);
    }
    private void initUI() {
        editUsername = (EditText) findViewById(R.id.username);
        editUsername.getText();
        editPassword = (EditText) findViewById(R.id.loginPassword);
        editPassword.getText();
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnLogin) {
                    if (editUsername.getText().toString().equals(username[0]) && editPassword.getText().toString().equals(pass[0])) {
                        txtStatus.setText("Login success!");
                        Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                        dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(dashboard);
                    } else
                    if (editUsername.getText().toString().equals(username[1]) && editPassword.getText().toString().equals(pass[1])) {
                        txtStatus.setText("Login success!");
                        Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                        dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(dashboard);
                    }
                    else {
                        txtStatus.setText("Username atau password salah.");
                    }
                }
            }
        });
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        username = new String[5];
        username[0] = "monica";
        username[1] = "admin";

        pass = new String[5];
        pass[0] = "1234";
        pass[1] = "admin";
    }


}



