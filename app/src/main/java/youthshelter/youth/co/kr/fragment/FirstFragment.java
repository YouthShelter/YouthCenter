package youthshelter.youth.co.kr.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.adapter.FirstFragmentUserRecyclerAdapter;
import youthshelter.youth.co.kr.data.YouthCenter;

public class FirstFragment extends Fragment {
    private RecyclerView firstRecyclerView;
    public FirstFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        firstRecyclerView = (RecyclerView)view.findViewById(R.id.firstRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* ArrayList<YouthCenter> centers = new ArrayList<>();
        for(int i=0; i<10; i++) {
            centers.add(new YouthCenter("1","더 플레이스","노원구","06:00 - 17:00",1234));
        }*/
        ArrayList<YouthCenter> youthCenters = (ArrayList<YouthCenter>)getArguments().getSerializable("centers");

        for(YouthCenter center : youthCenters)
            Log.i("tttttt",center.getName() + "~~~");
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirstFragmentUserRecyclerAdapter adapter = new FirstFragmentUserRecyclerAdapter(getActivity());
        firstRecyclerView.setAdapter(adapter);

        adapter.setItem(youthCenters);
    }
}
