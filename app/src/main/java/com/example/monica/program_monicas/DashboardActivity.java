package com.example.monica.program_monicas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnInput;
    private Button btnView;
    private Button btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnInput = (Button) findViewById(R.id.btnInput);
        btnView = (Button) findViewById(R.id.btnView);
        btnMaps = (Button) findViewById(R.id.btnMaps);

        btnInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent input = new Intent(getApplicationContext(), InputActivity.class);
                input.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(input);

            }
        });

        btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent view = new Intent(getApplicationContext(), ViewActivity.class);
                view.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(view);

            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent map = new Intent(getApplicationContext(), MapActivity.class);
                map.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(map);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);


            }
        });
    }
}
