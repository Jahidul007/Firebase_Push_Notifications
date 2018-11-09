package com.jahid.firebasepushnotifications;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView profile, user, notification;

    private ViewPager viewPager;

    private PageViewAdapter pageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent  = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

        profile = (TextView) findViewById(R.id.profileId);
        user = (TextView) findViewById(R.id.usersId);
        notification = (TextView) findViewById(R.id.notificationId);

        viewPager =(ViewPager) findViewById(R.id.viewPager);

        pageViewAdapter = new PageViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(0);

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(1);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(2);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                
                changeTabs(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void changeTabs(int position) {

        if(position == 0) {
            profile.setTextColor(getColor(R.color.colorAccent));
            profile.setTextSize(22);

            user.setTextColor(getColor(R.color.colorPrimary));
            user.setTextSize(16);

            notification.setTextColor(getColor(R.color.colorPrimary));
            notification.setTextSize(16);

        }

        if(position == 1) {
            profile.setTextColor(getColor(R.color.colorPrimary));
            profile.setTextSize(16);

            user.setTextColor(getColor(R.color.colorAccent));
            user.setTextSize(22);

            notification.setTextColor(getColor(R.color.colorPrimary));
            notification.setTextSize(16);

        }

        if(position == 2) {
            profile.setTextColor(getColor(R.color.colorPrimary));
            profile.setTextSize(16);

            user.setTextColor(getColor(R.color.colorPrimary));
            user.setTextSize(16);

            notification.setTextColor(getColor(R.color.colorAccent));
            notification.setTextSize(22);

        }
    }
}
