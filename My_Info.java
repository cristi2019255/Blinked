package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class My_Info extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner GroupsValue;
    private FirebaseUser fuser;
    private DatabaseReference myref;
    private EditText name,email,phone,city;
    private TextView DateValue,grupaValue;
    private ImageView imageView;
    private Button Submit,ChooseDate;
    UserS userS=new UserS("","","DEFAULT");
    String DatePicked="None";
    private String Changes;
    private static HashMap<String, Object> MAP=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting spinner for groups selection
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__info);
        GroupsValue=(Spinner)findViewById(R.id.GrupaValue);
        final ArrayAdapter<String> groups=new ArrayAdapter<String>(My_Info.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Grupe));
        groups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GroupsValue.setAdapter(groups);
        //
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        myref= FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        name=(EditText)findViewById(R.id.NumeValues);
        email=(EditText)findViewById(R.id.EmailValue);
        email.setText(fuser.getEmail());
        phone=(EditText)findViewById(R.id.PhoneVlaue);
        imageView=(ImageView)findViewById(R.id.image1);
        DateValue=(TextView)findViewById(R.id.DateValue);
        grupaValue=(TextView)findViewById(R.id.grupaValue);
        city=(EditText)findViewById(R.id.CityValue);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userS =dataSnapshot.getValue(UserS.class);
                phone.setText(dataSnapshot.child("Phone").getValue().toString());
                city.setText(dataSnapshot.child("City").getValue().toString());
                name.setText(dataSnapshot.child("Name").getValue().toString());
                DateValue.setText(dataSnapshot.child("BirthDate").getValue().toString());
                String s=dataSnapshot.child("Group").getValue().toString();
                grupaValue.setText(s);
                String[] sgrp=getResources().getStringArray(R.array.Grupe);
                for (int i=0;i<sgrp.length;i++){
                    if (sgrp[i]==s){
                        GroupsValue.setScrollBarDefaultDelayBeforeFade(i);
                        break;
                    }
                }
                if(userS.getImageURL().equals("DEFAULT")){
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Glide.with(getBaseContext()).load(userS.getImageURL()).into(imageView);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        ChooseDate=(Button)findViewById(R.id.DatePicker);
        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker =new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        Submit=(Button)findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changes="";
                if (!name.getText().toString().equals(fuser.getDisplayName())){
                    UserProfileChangeRequest profileUdates= new UserProfileChangeRequest.Builder()
                            .setDisplayName(name.getText().toString()).build();
                    fuser.updateProfile(profileUdates);
                }
                if (!email.getText().toString().equals(fuser.getEmail())){
                    //TO DO
                    //very hard !!! with verification email...
                }
                final String Group=GroupsValue.getSelectedItem().toString();
                final String City=city.getText().toString().trim();
                final String Name=name.getText().toString().trim();
                final String Phone=phone.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

                HashMap<String,Object> map=new HashMap<>();
                map.put("Name",Name);
                map.put("City",City);
                if (!Group.equals("None")){map.put("Group",Group);}
                if(!DatePicked.equals("None")){map.put("BirthDate",DatePicked);}
                if(Phone.length()==10){map.put("Phone",Phone);}
                MAP =map;
                reference.updateChildren(MAP);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        month++;
        DatePicked=dayOfMonth+"/"+month+"/"+year;
        Toast.makeText(getBaseContext(), currentDateString, Toast.LENGTH_SHORT).show();
    }
}
