package youthshelter.youth.co.kr.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import youthshelter.youth.co.kr.activity.MainActivity;
import youthshelter.youth.co.kr.R;

public class MainFragment extends Fragment {

    private Animation animation;
    private Animation.AnimationListener animationListener;
    public MainFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_intro, container, false);
        initAnimation();

        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext() , R.anim.alpha);
        animation.setAnimationListener(animationListener);

        view.startAnimation(animation);
        return view;
    }
    public void initAnimation() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

    }
}