package youthshelter.youth.co.kr.activity;


import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import youthshelter.youth.co.kr.adapter.ViewPagerAdapter;
import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.components.CustomViewPager;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private CustomViewPager viewPager;

    private MessageHandler messageHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        messageHandler = new MessageHandler();
        viewPager = (CustomViewPager) findViewById(R.id.main_viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),this, messageHandler);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setSwipeEnabled(false);
        viewPager.set

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

    };
}
