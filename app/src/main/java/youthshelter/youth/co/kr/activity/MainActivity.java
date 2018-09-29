package youthshelter.youth.co.kr.activity;


import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import youthshelter.youth.co.kr.adapter.ViewPagerAdapter;
import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.components.CustomViewPager;
import youthshelter.youth.co.kr.data.YouthCenter;


public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private CustomViewPager viewPager;

    private MessageHandler messageHandler;


    ArrayList<YouthCenter> youthCenters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youthCenters = ( ArrayList<YouthCenter>)getIntent().getSerializableExtra("centers");
        Log.i("tttttt",youthCenters.size()+"");


        messageHandler = new MessageHandler();
        viewPager = (CustomViewPager) findViewById(R.id.main_viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this, messageHandler,youthCenters);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setSwipeEnabled(false);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    class MessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

            }
        }

    }

    ;
}
