package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.blinked.Fragments.Alert_fragment;
import com.example.blinked.Fragments.Alert_my_com_fragment;

import java.util.ArrayList;
import java.util.List;

public class ViewMyCommunityAlert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_community_alert);
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
        ViewPager viewPager=findViewById(R.id.view_pager_alert);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Alert_my_com_fragment());
        viewPager.setAdapter(viewPagerAdapter);

    }

}
