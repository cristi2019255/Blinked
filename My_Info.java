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
    private ImageView imageView;
    private Button Submit,ChooseDate;
    UserS userS=new UserS("","","DEFAULT");
    String DatePicked="None";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting spinner for groups selection
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__info);
        GroupsValue=(Spinner)findViewById(R.id.GrupaValue);
        ArrayAdapter<String> groups=new ArrayAdapter<String>(My_Info.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Grupe));
        groups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GroupsValue.setAdapter(groups);
        //
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        myref= FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        name=(EditText)findViewById(R.id.NumeValue);
        name.setText(fuser.getDisplayName());
        email=(EditText)findViewById(R.id.EmailValue);
        email.setText(fuser.getEmail());
        phone=(EditText)findViewById(R.id.PhoneVlaue);
        imageView=(ImageView)findViewById(R.id.image1);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userS =dataSnapshot.getValue(UserS.class);
                phone.setText(dataSnapshot.child("Phone").getValue().toString());
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

        city=(EditText)findViewById(R.id.CityValue);
        Submit=(Button)findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Changes="";
                if (!name.getText().toString().equals(fuser.getDisplayName())){
                    UserProfileChangeRequest profileUdates= new UserProfileChangeRequest.Builder()
                            .setDisplayName(name.getText().toString()).build();
                    fuser.updateProfile(profileUdates);
                    Changes+="Name Changed ";
                }
                if (!email.getText().toString().equals(fuser.getEmail())){
                    //TO DO
                    //very hard !!! with verification email...
                }
                String Group=GroupsValue.getSelectedItem().toString();
                String City=city.getText().toString();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                HashMap<String,Object> map=new HashMap<>();
                if (!City.equals("None")){map.put("City",City);Changes+="City Changed ";}
                if(!Group.equals("None")){map.put("Group",Group);Changes+="Group Changed ";}
                if(!DatePicked.equals("None")){map.put("BirthDate",DatePicked);Changes+="BirthDate Changed ";}
                reference.updateChildren(map);

                Toast.makeText(getBaseContext(), Changes, Toast.LENGTH_LONG).show();
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
