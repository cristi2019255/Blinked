package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.blinked.Adapter.AlertAdapter;
import com.example.blinked.Adapter.FriendAdapter;
import com.example.blinked.Fragments.Add_new_friend;
import com.example.blinked.Fragments.Alert_fragment;
import com.example.blinked.Fragments.Community;
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

public class ViewOthersAlert extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Alert>mAlerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_others_alert);
        class ViewPagerAdapter extends FragmentPagerAdapter {
            private ArrayList<Fragment> fragments;
            ViewPagerAdapter (FragmentManager fm){
                super(fm);
                this.fragments=new ArrayList<>();
            }
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            public void addFragment(Fragment fragment){
                fragments.add(fragment);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }

        }
        ViewPager viewPager=findViewById(R.id.view_pager_alert_others);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Alert_fragment());
        viewPager.setAdapter(viewPagerAdapter);

    }


}
