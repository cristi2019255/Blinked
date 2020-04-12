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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.blinked.Adapter.UserAdapter;
import com.example.blinked.My_Info;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Add_new_friend extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;
    private EditText toFind;
    private ImageButton toFindButton;
    private Spinner Filtru;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_new_friend,container,false);
        toFind=(EditText)view.findViewById(R.id.toFind);
        toFindButton=(ImageButton)view.findViewById(R.id.findButton);
        Filtru=(Spinner)view.findViewById(R.id.filter);
        final ArrayAdapter<String> filters=new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Filtre));
        filters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Filtru.setAdapter(filters);

        recyclerView=view.findViewById(R.id.recicler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsers= new ArrayList<>();
        readCommunity1();
        toFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCommunity1(toFind.getText().toString().trim().toLowerCase(),Filtru.getSelectedItem().toString());
            }
        });
        return view;
    }


    private void readCommunity1() {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.orderByPriority();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recyclerView.removeAllViews();
                mUsers=new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    GenericTypeIndicator<HashMap<String,Object>>t=new GenericTypeIndicator<HashMap<String, Object>>() {};
                    HashMap<String,Object>Friends=dataSnapshot.child(firebaseUser.getUid()).child("Friends").getValue(t);
                    if (!user.getID().equals(firebaseUser.getUid())) {
                        if(Friends!=null) {
                            if (!Friends.containsKey(user.getID())) {
                                mUsers.add(user);
                            }
                        }else{mUsers.add(user);}
                    }
                    //reference.child(user.getID()).setPriority(-user.getPopularity());
                }
                userAdapter = new UserAdapter(getContext(), mUsers);
                recyclerView.setAdapter(userAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void readCommunity1(final String tofind, final String filer) {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.removeAllViews();

        //get current date
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String [] datenow=currentDate.split("-");
        final int zzn=Integer.parseInt(datenow[0]);
        final int mmn=Integer.parseInt(datenow[1]);
        final int yyn=Integer.parseInt(datenow[2]);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    GenericTypeIndicator<HashMap<String,Object>>t=new GenericTypeIndicator<HashMap<String, Object>>() {};
                    HashMap<String,Object>Friends=dataSnapshot.child(firebaseUser.getUid()).child("Friends").getValue(t);
                    if (!user.getID().equals(firebaseUser.getUid())) {
                        if(Friends!=null) {
                            if (!Friends.containsKey(user.getID())) {
                                if(((filer.equals("Nume"))&&(tofind.equals(user.getName().toLowerCase())))||
                                (filer.equals("Grupa")&&(tofind.equals(user.getGroup().toLowerCase())))){
                                    mUsers.add(user);
                                }else if (filer.equals("Virsta")){
                                    if (!user.getBirthDate().equals("None"))
                                    try {
                                        String [] dates=user.getBirthDate().split("/");
                                        int zz=Integer.parseInt(dates[0]);
                                        int mm=Integer.parseInt(dates[1]);
                                        int yy=Integer.parseInt(dates[2]);
                                        int years=yyn-yy-1;
                                        if (mm<mmn){
                                            years++;
                                        }
                                        if ((mm==mmn)&&(zzn>zz)){
                                            years++;
                                        }
                                        if (Integer.parseInt(tofind)==years){
                                            mUsers.add(user);
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                        else{
                            if(((filer.equals("Nume"))&&(tofind.equals(user.getName().toLowerCase())))||
                                    (filer.equals("Grupa")&&(tofind.equals(user.getGroup().toLowerCase())))){
                            mUsers.add(user);
                            }else if (filer.equals("Virsta")){
                                if (!user.getBirthDate().equals("None"))
                                try {
                                    String [] dates=user.getBirthDate().split("/");
                                    int zz=Integer.parseInt(dates[0]);
                                    int mm=Integer.parseInt(dates[1]);
                                    int yy=Integer.parseInt(dates[2]);
                                    int years=yyn-yy-1;
                                    if (mm<mmn){
                                        years++;
                                    }
                                    if ((mm==mmn)&&(zzn>zz)){
                                        years++;
                                    }
                                    if (Integer.parseInt(tofind)==years){
                                        mUsers.add(user);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                userAdapter = new UserAdapter(getContext(), mUsers);
                recyclerView.setAdapter(userAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

