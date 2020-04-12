package com.example.blinked.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blinked.Alert;
import com.example.blinked.R;
import com.example.blinked.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder>{
    private Context mContext;
    private List<Alert> mUsers;
    private Alert alert;
    private DatabaseReference refUsers;
    private FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    public AlertAdapter(Context mContext, List<Alert> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        refUsers= FirebaseDatabase.getInstance().getReference("Users");
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username,virsta,oras,grupa,descriere,dataValue,PhoneValue;
        public ImageView profile_Image;
        public Button remove_alert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /**to do**/
            username=itemView.findViewById(R.id.usernameAlert);
            profile_Image=itemView.findViewById(R.id.profile_imageAlert);
            virsta=itemView.findViewById(R.id.Virsta1);
            oras=itemView.findViewById(R.id.Oras);
            grupa=itemView.findViewById(R.id.Grupa);
            descriere=itemView.findViewById(R.id.Descriere);
            dataValue=itemView.findViewById(R.id.dateAlert);
            PhoneValue=itemView.findViewById(R.id.PhoneVlaue);
            remove_alert=itemView.findViewById(R.id.Remove_alert);
        }
    }

    @NonNull
    @Override
    public AlertAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.alert_item,parent,false);
        return new AlertAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlertAdapter.ViewHolder holder, final int position) {
        alert=mUsers.get(position);
        final DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Alerte").child(alert.getID());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Alert alert=dataSnapshot.getValue(Alert.class);
              holder.descriere.setText(alert.getDetails());
              holder.dataValue.setText(alert.getDate());
              holder.virsta.setText(alert.getYears() +"ani ");
              holder.oras.setText(" "+alert.getCity()+" ");
              holder.grupa.setText(" "+alert.getGroup()+" ");
              holder.PhoneValue.setText(" "+alert.getPhone()+" ");
              if ((alert.getID_placer().equals(firebaseUser.getUid()))||
                (alert.getID().equals(firebaseUser.getUid()))){
              holder.remove_alert.setVisibility(View.VISIBLE);
              }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        DatabaseReference refAlert=refUsers.child(alert.getID());
        refAlert.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user=dataSnapshot.getValue(User.class);
                    holder.username.setText(user.getName());
                    if (user.getImageURL().equals("DEFAULT")){
                            holder.profile_Image.setImageResource(R.mipmap.ic_launcher);
                    }else{
                        Glide.with(mContext).load(user.getImageURL()).into(holder.profile_Image);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        holder.remove_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.setValue(null);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}

