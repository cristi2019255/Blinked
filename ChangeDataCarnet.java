package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChangeDataCarnet extends AppCompatActivity {
    private EditText nume,datanasterii,codcarnet;
    private Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data_carnet);
        nume=findViewById(R.id.NumeValue1);
        datanasterii=findViewById(R.id.VirstaValue1);
        codcarnet=findViewById(R.id.CodCarnetValue1);
        change=findViewById(R.id.ChangeFinal);

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                nume.setText(user.getName());
                datanasterii.setText(user.getBirthDate());
                codcarnet.setText(user.getCodCarnet());
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object>map=new HashMap<>();
                map.put("Name",nume.getText().toString().trim());
                map.put("BirthDate",datanasterii.getText().toString().trim());
                map.put("CodCarnet",codcarnet.getText().toString().trim());
                reference.updateChildren(map);
                finish();
            }
        });
    }
}