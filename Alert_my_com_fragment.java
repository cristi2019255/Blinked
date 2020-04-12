package com.example.blinked.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blinked.Adapter.AlertAdapter;
import com.example.blinked.Alert;
import com.example.blinked.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alert_my_com_fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Alert> mAlerts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_alert_my_com_fragment,container,false);
        recyclerView=view.findViewById(R.id.recicler3_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAlerts= new ArrayList<>();
        readCommunity();
        return view;
    }

    private void readCommunity() {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference refAlerte= FirebaseDatabase.getInstance().getReference("Alerte");
        final DatabaseReference refUsers=FirebaseDatabase.getInstance().getReference("Users")
                .child(firebaseUser.getUid()).child("Friends");

        refAlerte.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    mAlerts.clear();
                    final Alert alert=snapshot.getValue(Alert.class);
                    refUsers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            GenericTypeIndicator<HashMap<String,Object>> t=new GenericTypeIndicator<HashMap<String, Object>>() {};
                            HashMap<String,Object>map=dataSnapshot1.getValue(t);
                            if (map==null){map=new HashMap<>();}
                            if (alert.getID().equals(firebaseUser.getUid())){mAlerts.add(alert);}
                            for (final Map.Entry<String,Object> entry :map.entrySet()) {
                                if ((entry.getKey().equals(alert.getID()))) {
                                    mAlerts.add(alert);
                                }
                            }

                            AlertAdapter alertAdapter = new AlertAdapter(getContext(), mAlerts);
                            recyclerView.setAdapter(alertAdapter);

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
