package com.example.blinked.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blinked.R;
import com.example.blinked.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUsers;
    private User user=new User();


    private FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username,nr_donari;
        public ImageView profile_Image;
        public Button add_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            nr_donari=itemView.findViewById(R.id.nr_donari);
            profile_Image=itemView.findViewById(R.id.profile_image);
            add_button=itemView.findViewById(R.id.add);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        user=mUsers.get(position);
        holder.username.setText(user.getName());
        holder.nr_donari.setText("Donări "+user.getPopularity());
        if (user.getImageURL().equals("DEFAULT")){
            holder.profile_Image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_Image);
        }

        holder.add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object>map=new HashMap<>();
                map.put(mUsers.get(position).getID(),mUsers.get(position).getID());
                reference.child("Friends").updateChildren(map);
                HashMap<String,Object>map1=new HashMap<>();
                map1.put(fuser.getUid(),fuser.getUid());
                DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("Users")
                        .child(mUsers.get(position).getID()).child("Friends");
                reference1.updateChildren(map1);
                TranslateAnimation animate = new TranslateAnimation(
                                0,                 // fromXDelta
                                holder.itemView.getWidth(),                 // toXDelta
                                0,                 // fromYDelta
                                0); // toYDelta
                animate.setDuration(500);
                animate.setFillAfter(true);
                holder.itemView.startAnimation(animate);
                holder.itemView.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
