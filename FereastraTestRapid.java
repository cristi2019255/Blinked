package com.example.blinked;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FereastraTestRapid extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fereastra_test_rapid);
        Button button = (Button) findViewById(R.id.buttonTestRapidVerifica);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg1 = (RadioGroup) findViewById(R.id.rgr1);
                RadioGroup rg2 = (RadioGroup) findViewById(R.id.rgr2);
                RadioGroup rg3 = (RadioGroup) findViewById(R.id.rgr3);
                RadioGroup rg4 = (RadioGroup) findViewById(R.id.rgr4);
                RadioGroup rg5 = (RadioGroup) findViewById(R.id.rgr5);
                RadioGroup rg6 = (RadioGroup) findViewById(R.id.rgr6);
                RadioGroup rg7 = (RadioGroup) findViewById(R.id.rgr7);
                RadioGroup rg8 = (RadioGroup) findViewById(R.id.rgr8);
                RadioGroup rg9 = (RadioGroup) findViewById(R.id.rgr9);
                RadioGroup rg10 = (RadioGroup) findViewById(R.id.rgr10);
                RadioButton selectat1 = (RadioButton) findViewById(rg1.getCheckedRadioButtonId());
                RadioButton selectat2 = (RadioButton) findViewById(rg2.getCheckedRadioButtonId());
                RadioButton selectat3 = (RadioButton) findViewById(rg3.getCheckedRadioButtonId());
                RadioButton selectat4 = (RadioButton) findViewById(rg4.getCheckedRadioButtonId());
                RadioButton selectat5 = (RadioButton) findViewById(rg5.getCheckedRadioButtonId());
                RadioButton selectat6 = (RadioButton) findViewById(rg6.getCheckedRadioButtonId());
                RadioButton selectat7 = (RadioButton) findViewById(rg7.getCheckedRadioButtonId());
                RadioButton selectat8 = (RadioButton) findViewById(rg8.getCheckedRadioButtonId());
                RadioButton selectat9 = (RadioButton) findViewById(rg9.getCheckedRadioButtonId());
                RadioButton selectat10 = (RadioButton) findViewById(rg10.getCheckedRadioButtonId());
                if(!selectat1.getText().equals("DA") || !selectat2.getText().equals("DA") ||selectat3.getText().equals("DA") ||
                        selectat4.getText().equals("DA") ||selectat5.getText().equals("DA") ||selectat6.getText().equals("DA") ||
                        selectat7.getText().equals("DA") ||selectat8.getText().equals("DA") ||selectat9.getText().equals("DA") ||
                        selectat10.getText().equals("DA")) {
                    startActivity(new Intent(FereastraTestRapid.this, Profile.class));
                    Toast.makeText(FereastraTestRapid.this, "Ne pare rau, nu puteti dona!", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(FereastraTestRapid.this, Fereastra_informatii_autoexcludere.class));
                }
            }
        });
    }
}
