package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Donator_card extends AppCompatActivity {
    private TextView Nume,Virsta,UltimaDonare,Cod,Days_left,Blinked_donations;
    private Button Change,AmDonat;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_card);
        Nume=findViewById(R.id.NumeValue);
        Virsta=findViewById(R.id.VirstaValue);
        UltimaDonare=findViewById(R.id.UltimaDonareValue);
        Cod=findViewById(R.id.CodCarnetValue);
        Days_left=findViewById(R.id.zile_left);
        Blinked_donations=findViewById(R.id.Blinked_donations);
        Change=findViewById(R.id.ChangeFinal);
        AmDonat=findViewById(R.id.AmDonat);


        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                Nume.setText(user.getName());
                Virsta.setText(user.getBirthDate());
                UltimaDonare.setText(user.getLastDonation());
                Cod.setText(user.getCodCarnet());
                Blinked_donations.setText("Donari cu Blinked: "+user.getPopularity());
                //to do days left
                if (!user.getLastDonation().equals("None")){
                SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
                Date d=null;
                try {
                    d=formatter.parse(user.getLastDonation());
                }catch (ParseException e){
                    e.printStackTrace();
                }
                Calendar thatday=Calendar.getInstance();
                thatday.setTime(d);
                Calendar today=Calendar.getInstance();
                long diff=today.getTimeInMillis()-thatday.getTimeInMillis();
                diff = diff / (24 * 60 * 60 * 1000);
                diff=60-diff;
                if(diff>0){
                    Days_left.setText(diff+" zile pina la donare");
                }
                else{
                    Days_left.setVisibility(View.GONE);
                    AmDonat.setVisibility(View.VISIBLE);
                }
                }else{
                    Days_left.setVisibility(View.GONE);
                    AmDonat.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        AmDonat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Donator_card.this, AmDonat.class));
                finish();
            }
        });
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Donator_card.this,ChangeDataCarnet.class));
                finish();
            }
        });

    }
}
