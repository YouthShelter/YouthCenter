package youthshelter.youth.co.kr.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.adapter.ImageViewPagerAdapter;
import youthshelter.youth.co.kr.components.CircleAnimIndicator;
import youthshelter.youth.co.kr.data.YouthCenter;

public class DetailActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private ArrayList<String> numberList;

    private CircleAnimIndicator circleAnimIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        YouthCenter center = (YouthCenter)getIntent().getSerializableExtra("center");
        Log.i("ttttt",center.getCenterLocation());
        ArrayList<Integer> imageURL = new ArrayList<>();

        imageURL.add(R.drawable.heart);
        imageURL.add(R.drawable.heart);
        imageURL.add(R.drawable.heart);
        imageURL.add(R.drawable.heart);
        imageURL.add(R.drawable.heart);
        viewPager = (ViewPager) findViewById(R.id.imageViewPager);
        circleAnimIndicator = (CircleAnimIndicator) findViewById(R.id.circleAnimIndicator);

        initIndicaotor(imageURL.size());

        ImageViewPagerAdapter viewPagerAdapter = new ImageViewPagerAdapter(getSupportFragmentManager(),this, imageURL);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);


    }
    private void initIndicaotor(int size){

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(size, R.drawable.indicator_non , R.drawable.indicator_on);
    }
    /**
     * ViewPager 전환시 호출되는 메서드
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            circleAnimIndicator.selectDot(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
