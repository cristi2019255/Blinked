package com.example.blinked.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blinked.Adapter.FriendAdapter;
import com.example.blinked.R;
import com.example.blinked.User;
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

public class Community extends Fragment {
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private List<User> mUsers;
    private HashMap<String,Object>map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_community,container,false);
        recyclerView=view.findViewById(R.id.recicler1_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsers= new ArrayList<>();
        readCommunity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //mUsers= new ArrayList<>();
        //readCommunity();
    }


    private void readCommunity() {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").
                child(firebaseUser.getUid()).child("Friends");
        final DatabaseReference refUsers=FirebaseDatabase.getInstance().getReference("Users");
        mUsers=new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,Object>>t=new GenericTypeIndicator<HashMap<String, Object>>() {};
                map=dataSnapshot.getValue(t);
                if (map==null){map=new HashMap<>();}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        refUsers.orderByPriority();
        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                recyclerView.removeAllViews();
                mUsers=new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot1.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (map.containsValue(user.getID())) {
                        mUsers.add(user);
                    }
                }
                    friendAdapter = new FriendAdapter(getContext(), mUsers);
                    recyclerView.setAdapter(friendAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
