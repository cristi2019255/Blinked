package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AmDonat extends AppCompatActivity {
    private TextView popularitate;
    private Button submit;
    private EditText id_to_veify;
    private int popularity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_donat);
        popularitate=findViewById(R.id.popularitate);
        submit=findViewById(R.id.submit_donation);
        id_to_veify=findViewById(R.id.unique_cod_donation);

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                popularity=user.getPopularity();
                popularitate.setText("Numarul de donari de pina acum: "+popularity);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final DatabaseReference reference_donations=FirebaseDatabase.getInstance().getReference("Donations");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id=id_to_veify.getText().toString().trim();
                reference_donations.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            if (id.equals(snapshot.getKey())){
                                popularity++;
                                Toast.makeText(getBaseContext(), "Ești un erou!!!", Toast.LENGTH_SHORT).show();
                                HashMap<String,Object> map=new HashMap<>();
                                map.put("Popularity",popularity);
                                map.put("LastDonation",snapshot.getValue());
                                reference.updateChildren(map);
                                //new line of code
                                reference.setPriority(-popularity);
                                //
                                reference_donations.child(id).setValue(null);
                                break;
                            }
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
