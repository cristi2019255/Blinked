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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{
    private Context mContext;
    private List<User> mUsers;
    private User user=new User();

    private FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid()).child("Friends");

    public FriendAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username,nr_donari;
        public ImageView profile_Image;
        public Button remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            profile_Image=itemView.findViewById(R.id.profile_image);
            remove=itemView.findViewById(R.id.Remove);
            nr_donari=itemView.findViewById(R.id.nr_donari_f);
        }
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.friend_item,parent,false);
        return new FriendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendAdapter.ViewHolder holder, final int position) {
        user=mUsers.get(position);
        holder.username.setText(user.getName());
        holder.nr_donari.setText("Donări "+user.getPopularity());
        if (user.getImageURL().equals("DEFAULT")){
            holder.profile_Image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_Image);
        }

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mUsers.get(position).getName());
                DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("Users")
                        .child(user.getID()).child("Friends");
                reference1.child(fuser.getUid()).setValue(null);
                reference.child(mUsers.get(position).getID()).setValue(null);
                TranslateAnimation animate = new TranslateAnimation(
                        0,                // fromXDelta
                         holder.itemView.getWidth(), // toXDelta
                        0,                // fromYDelta
                        0);                 // toYDelta
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
