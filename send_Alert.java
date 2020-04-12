package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blinked.Fragments.Alert_fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class send_Alert extends AppCompatActivity {
    private Button Submit;
    private EditText group,city,virsta,phone,details,email;
    private Spinner GroupsValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__alert);
        Toast.makeText(send_Alert.this, "Toate câmpurile sunt necesare la completare", Toast.LENGTH_SHORT).show();
        addListener();
    }


    private void send_notification_new_alert(){
        String message="Cineva are nevoie de sînge acum.";
        NotificationCompat.Builder builder=new NotificationCompat.Builder(
                send_Alert.this
        ).setSmallIcon(R.drawable.ic_announcement_red_24dp)
                .setContentTitle("Alertă")
                .setContentText(message)
                .setAutoCancel(true);
        Intent intent=new Intent(send_Alert.this,
                Alert_fragment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(send_Alert.this,
                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(
                Context.NOTIFICATION_SERVICE
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        notificationManager.notify(0,builder.build());
    }

    private void send_notification_alert(){
        //send a notification to all users with one signal and firebase

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic ZTVhNmY1NmItOTU0YS00ZTNhLWFkZjctZDc2ZjVjNGNiOWRh");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"7e83fa08-8aa2-4221-a6f4-588d91d0c2cb\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"exists\"}],"
                                //+ "\"small_icon\": {\"@android\": \"drawable/ic_android_black_24dp.xml\"},"
                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"Cineva are nevoie de singe acum.\"}"
                                + "}";


                        //System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        //System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        //System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    private void addListener() {
     details=(EditText)findViewById(R.id.DetailsValue);
     email=(EditText)findViewById(R.id.EmailValue);
     phone=(EditText)findViewById(R.id.TelefonVal);
        virsta=(EditText)findViewById(R.id.VirstaVal);
        city=(EditText)findViewById(R.id.OrasVal);
        group=(EditText)findViewById(R.id.GrupaVal);
     Submit=(Button)findViewById(R.id.Submit);
     Submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Alerte");
             DatabaseReference refUsers= FirebaseDatabase.getInstance().getReference("Users");
             final String detailss=details.getText().toString().trim();
             final String emails=email.getText().toString().trim();
             final String groups=group.getText().toString().trim();
             final String virstas=virsta.getText().toString().trim();
             final String citys=city.getText().toString().trim();
             final String phones=phone.getText().toString().trim();

             if(emails.equals("")){
                 Toast.makeText(getBaseContext(), "Email invalid", Toast.LENGTH_SHORT).show();
                 return;
             }
             if(groups.equals("")){
                 Toast.makeText(getBaseContext(), "Grupa invalida", Toast.LENGTH_SHORT).show();
                 return;
             }
             if((virstas.equals(""))){
                 Toast.makeText(getBaseContext(), "Virsta invalid", Toast.LENGTH_SHORT).show();
                 return;
             }
             try {
                 Integer.parseInt(virstas);
             }catch (Exception e){
                 Toast.makeText(getBaseContext(), "Virsta invalid", Toast.LENGTH_SHORT).show();
                 return;
             }
             if(citys.equals("")){
                 Toast.makeText(getBaseContext(), "Oras invalid", Toast.LENGTH_SHORT).show();
                 return;
             }
             if((phones.equals(""))||(phones.length()!=10)){
                 Toast.makeText(getBaseContext(), "Telefon invalid", Toast.LENGTH_SHORT).show();
                 return;
             }
             if (detailss.length()>100){
                 Toast.makeText(getBaseContext(), "Descriere prea lungă", Toast.LENGTH_SHORT).show();
                 return;
             }
             final FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
             refUsers.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                            User user=snapshot.getValue(User.class);
                            if (user.getEmail().equals(emails)){
                             DatabaseReference refU=reference.child(user.getID());
                             HashMap<String,Object> map=new HashMap<>();
                             map.put("Email",emails);
                             map.put("Details",detailss);
                             map.put("phone",phones);
                             map.put("group",groups);
                             map.put("city",citys);
                             map.put("years",virstas);
                             map.put("ID",user.getID());
                             map.put("ID_placer",firebaseUser.getUid());
                             String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                             map.put("Date",currentDate);
                             refU.updateChildren(map);
                            }
                     }
                 }
                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {
                 }
             });
             Toast.makeText(getBaseContext(), "Alerta a fost trimisa cu succes", Toast.LENGTH_SHORT).show();
             send_notification_alert();
         }
     });
    }
}
