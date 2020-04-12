package com.example.blinked;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fereastra_informatii_autoexcludere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fereastra_informatii_autoexcludere);
        Button button = (Button) findViewById(R.id.buttonSpreAutoexcludere);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fereastra_informatii_autoexcludere.this, FereastraAutoexcludere.class));
            }
        });
    }
}
