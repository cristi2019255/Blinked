package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.blinked.Fragments.Community;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    private Button profile;
    private Button InfoGenerale,my_community,sendAlert,alerte_others,alerte_community;
    private HashMap<String,Object> idfriends=new HashMap<>();
    private FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final DatabaseReference refAlerts=FirebaseDatabase.getInstance().getReference("Alerte");
        refAlerts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String [] date_now=currentDate.split("/");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Alert alert=snapshot.getValue(Alert.class);
                    String [] date_alert = alert.getDate().split("/");
                    if (Integer.parseInt(date_alert[0])<=Integer.parseInt(date_now[0])-5){
                        refAlerts.child(alert.getID()).setValue(null);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        addOnActionListener();
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference userFriends= FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("Friends");
        DatabaseReference alertRef=FirebaseDatabase.getInstance().getReference("Alerte");

        userFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,Object>> t=new GenericTypeIndicator<HashMap<String, Object>>() {};
                idfriends=dataSnapshot.getValue(t);
                if (idfriends==null){idfriends=new HashMap<>();}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        alertRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int nr_friends_alert=0;
                int nr_others_alert=0;
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Alert alert =snapshot.getValue(Alert.class);
                    if ((idfriends.containsKey(alert.getID()))
                            ||(alert.getID().equals(firebaseUser.getUid()))){
                        nr_friends_alert++;
                    }else{
                        nr_others_alert++;
                    }
                }
                alerte_community.setText("Comunitatea mea: "+nr_friends_alert);
                alerte_others.setText("Alții: "+nr_others_alert);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void addOnActionListener() {
        profile=(Button)findViewById(R.id.contul_meu);
        InfoGenerale=(Button)findViewById(R.id.info);
        sendAlert=(Button)findViewById(R.id.trimite_alerta);
        my_community=(Button)findViewById(R.id.my_community);
        alerte_others=(Button)findViewById(R.id.alerte_others);
        alerte_community=(Button)findViewById(R.id.alerte_my_community);

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
                startActivity(new Intent(Main2Activity.this, My_Community.class));
            }
        });
        alerte_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, ViewMyCommunityAlert.class));
            }
        });
        alerte_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, ViewOthersAlert.class));
            }
        });
    }
}
