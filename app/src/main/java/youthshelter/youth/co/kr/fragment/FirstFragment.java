package youthshelter.youth.co.kr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.adapter.FirstFragmentCenterRecyclerAdapter;
import youthshelter.youth.co.kr.data.YouthCenter;

public class FirstFragment extends Fragment {
    private RecyclerView firstRecyclerView;
    private ArrayList<YouthCenter> youthCenters;
    private ArrayList<YouthCenter> filteringCenters;
    private FirstFragmentCenterRecyclerAdapter adapter;
    private boolean isFiltering = false;

    public FirstFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        firstRecyclerView = (RecyclerView) view.findViewById(R.id.firstRecyclerView);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ttttttt", requestCode + " " + resultCode + " " + data.getIntExtra("index", 0) + " " + data.getIntExtra("count", 0));
        if (requestCode == 3000) {
            if (resultCode == 100) {
                int index = data.getIntExtra("index", 0);
                if(isFiltering){
                    filteringCenters.get(index).setLike(data.getIntExtra("count", 0));
                }
                else{
                    youthCenters.get(index).setLike(data.getIntExtra("count", 0));
                }

                adapter.notifyItemChanged(index);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        filteringCenters = new ArrayList<>();
        youthCenters = (ArrayList<YouthCenter>) getArguments().getSerializable("centers");
        firstRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FirstFragmentCenterRecyclerAdapter(this);
        firstRecyclerView.setAdapter(adapter);


        //sortByName(youthCenters);
        adapter.setItem(youthCenters);
        //setArrayBySearch("도봉");
    }

    public void setArrayBySearch(String text) {
        isFiltering = true;
        filteringCenters.clear();
        for (YouthCenter youthCenter : youthCenters) {
            if (youthCenter.getName().contains(text)) {
                filteringCenters.add(youthCenter);
            }
        }
        adapter.setItem(filteringCenters);
    }

    public void sortByName(ArrayList<YouthCenter> youthCenters) {
        Collections.sort(youthCenters, new Comparator<YouthCenter>() {
            public int compare(YouthCenter obj1, YouthCenter obj2) {
                // ## Ascending order
                return obj1.getName().compareToIgnoreCase(obj2.getName());
            }
        });
    }

    public void sortByDistance(ArrayList<YouthCenter> youthCenters) {
        Collections.sort(youthCenters, new Comparator<YouthCenter>() {
            public int compare(YouthCenter obj1, YouthCenter obj2) {
                // ## Ascending order
                return Double.valueOf(obj1.getDistance()).compareTo(obj2.getDistance());
            }
        });

    }

    public void sortByLike(ArrayList<YouthCenter> youthCenters) {
        Collections.sort(youthCenters, new Comparator<YouthCenter>() {
            public int compare(YouthCenter obj1, YouthCenter obj2) {
                // ## Ascending order
                return Integer.valueOf(obj2.getLike()).compareTo(obj1.getLike());
            }
        });
    }
}
