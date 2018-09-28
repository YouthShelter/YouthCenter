package youthshelter.youth.co.kr.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.fragment.FirstFragment;
import youthshelter.youth.co.kr.fragment.SecondFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private Handler handler;
    public ViewPagerAdapter(FragmentManager fm, Context context, Handler handler) {
        super(fm);
        this.context = context;
        this.handler = handler;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            default:
                return null;
        }
    }

    @Override // ViewPager의 Page 수
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.firstFragment);
            case 1:
                return context.getResources().getString(R.string.secondFragment);
            default:
                return null;
        }
    }
}