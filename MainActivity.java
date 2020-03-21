package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button Inregistrare,Autorizare;
    RelativeLayout root;
    private RelativeLayout activity_main;
    private static int SIGN_IN_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("users");
        mAuth = FirebaseAuth.getInstance();

        activity_main=findViewById(R.id.root_element);
        root=findViewById(R.id.root_element);

        //user is not authorized
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user!=null){
                    //user is signed in

                }else{
                    //User is signed out

                }
            }
        };

        addOnActionListener();
    }

    private void addOnActionListener() {
        Inregistrare=(Button)findViewById(R.id.buttonRegister);
        Autorizare=(Button)findViewById(R.id.buttonSignIn);

        Inregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSignInWindow();
            }
        });

        Autorizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAuthorizationWindow();
            }
        });
        
        
    }
    
    private void ShowSignInWindow(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Inregistrare");
        dialog.setMessage("Complectati toate cimpurile pentru a va putea Inregistra");
        LayoutInflater inflater=LayoutInflater.from(this);
        View signinview=inflater.inflate(R.layout.inregistrare,null);
        dialog.setView(signinview);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final EditText email=signinview.findViewById(R.id.emailfield);
        final EditText pass=signinview.findViewById(R.id.passwordfield);
        final EditText phone=signinview.findViewById(R.id.phonefield);
        final EditText name=signinview.findViewById(R.id.namefield);


        dialog.setPositiveButton("Inregistrare", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(email.getText())) {
                            Snackbar.make(root,"nu ati introdus email",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (pass.getText().length() < 5) {
                            Snackbar.make(root,"parola e prea scurta",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (phone.getText().length() != 10) {
                            Snackbar.make(root,"numarul de telefon are 10 cifre",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(name.getText())) {
                            Snackbar.make(root,"Numele e invalid",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        String namedb = name.getText().toString();
                        String phonedb = phone.getText().toString();
                        String emaildb = email.getText().toString();
                        String passdb = pass.getText().toString();

                        mAuth.createUserWithEmailAndPassword(emaildb, passdb)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(MainActivity.this, "Inregistrare cu succes.",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(MainActivity.this, "Inregistrare esuata.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            // Name, email address, and profile photo Url
                            String name = user.getDisplayName();
                            String email = user.getEmail();
                            Uri photoUrl = user.getPhotoUrl();
                            String uid = user.getUid();

                            // Check if user's email is verified
                            boolean emailVerified = user.isEmailVerified();
                            if(!emailVerified){
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(MainActivity.this,"Verification email send",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{

                            }
                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use
                            // FirebaseUser.getIdToken() instead.

                        }

                    }
                });
        dialog.show();
    };

    private void ShowAuthorizationWindow(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Autentificare");
        dialog.setMessage("Complectati cimpurile");
        LayoutInflater inflater=LayoutInflater.from(this);
        View authorization=inflater.inflate(R.layout.autorizare,null);
        dialog.setView(authorization);

        final EditText email=authorization.findViewById(R.id.emailFieldA);
        final EditText pass=authorization.findViewById(R.id.passwordFieldA);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Autentificare", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"please enter your email",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().length()<5){
                    Snackbar.make(root,"parola prea scurta",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                String emaildb=email.getText().toString();
                String passdb=pass.getText().toString();
                mAuth.signInWithEmailAndPassword(emaildb,passdb).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Autorizare cu succes",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"Autorizare esuata!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        dialog.show();
    }

}
