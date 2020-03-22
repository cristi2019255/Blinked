package com.example.blinked;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button profile;
    private Button InfoGenerale,my_community,sendAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addOnActionListener();
    }

    private void addOnActionListener() {
        profile=(Button)findViewById(R.id.contul_meu);
        InfoGenerale=(Button)findViewById(R.id.info);
        sendAlert=(Button)findViewById(R.id.trimite_alerta);
        my_community=(Button)findViewById(R.id.my_community);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,Profile.class));
            }
        });

        InfoGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,InformatiiGenerale.class));
            }
        });
        sendAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,send_Alert.class));
            }
        });
        my_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,My_Community.class));
            }
        });
    }
}
