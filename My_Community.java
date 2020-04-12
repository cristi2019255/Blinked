package com.example.blinked;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.blinked.Fragments.Add_new_friend;
import com.example.blinked.Fragments.Community;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class My_Community extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__community);


        class ViewPagerAdapter extends FragmentPagerAdapter{
            private ArrayList<Fragment> fragments;
            private ArrayList<String> titles;
            ViewPagerAdapter (FragmentManager fm){
                super(fm);
                this.fragments=new ArrayList<>();
                this.titles=new ArrayList<>();
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

            public void addFragment(Fragment fragment,String title){
                fragments.add(fragment);
                titles.add(title);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

        }
        TabLayout tabLayout= findViewById(R.id.tab_layout);
        ViewPager viewPager=findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Community(),"Comunitatea mea");
        viewPagerAdapter.addFragment(new Add_new_friend(),"Adaugă noi prieteni");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
