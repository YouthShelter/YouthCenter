package youthshelter.youth.co.kr.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Handler;


import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.activity.DetailActivity;
import youthshelter.youth.co.kr.data.YouthCenter;

public class FirstFragmentUserRecyclerAdapter extends RecyclerView.Adapter<FirstFragmentUserRecyclerAdapter.UserViewHolder> {
    private ArrayList<YouthCenter> arrayList;
    private Activity context;
    private Handler handler;
    public FirstFragmentUserRecyclerAdapter(Activity context) {
        this.context = context;
        arrayList = new ArrayList();
    }
    public FirstFragmentUserRecyclerAdapter(Activity context, Handler handler) {
        this.context = context;
        this.handler = handler;
        arrayList = new ArrayList();
    }
    public void setItem(ArrayList<YouthCenter> centers){
        this.arrayList = centers;
        notifyDataSetChanged();
    }
    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shelter_image_ImageView;
        TextView shelter_location_TextView;
        TextView shelter_name_TextView;
        TextView shelter_like_TextView;
        TextView shelter_playTime_TextView;
        CardView cardView;

        public UserViewHolder(View itemView) {
            super(itemView);
            shelter_image_ImageView = (ImageView) itemView.findViewById(R.id.shelter_image_ImageView);
            shelter_location_TextView = (TextView) itemView.findViewById(R.id.shelter_location_TextView);
            shelter_name_TextView = (TextView) itemView.findViewById(R.id.shelter_name_TextView);
            shelter_like_TextView = (TextView) itemView.findViewById(R.id.shelter_like_TextView);
            shelter_playTime_TextView = (TextView) itemView.findViewById(R.id.shelter_playTime_TextView);
            cardView = (CardView) itemView.findViewById(R.id.dataCenter_recycler_view);
            cardView.setOnClickListener(this);
           /* cardView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int action = event.getAction();
                    Log.e("ttttt",action+" ");
                    if (action == MotionEvent.ACTION_MOVE && v.isPressed()) {
                        cardView.setBackgroundColor(Color.parseColor("#c6c4c4"));
                    }
                    else if(action == MotionEvent.ACTION_UP){
                        v.performClick();
                    }
                    else {

                        cardView.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    return true;
                }

            });*/
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,DetailActivity.class);
            intent.putExtra("center",arrayList.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }

    public void addItem(YouthCenter center) {
        arrayList.add(center);
        notifyItemInserted(arrayList.size()-1);//notifyItemInserted(arrayList.size()-1);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    private String doDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        String strWeek = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (nWeek == 1) {
            strWeek = "일요일";
        } else if (nWeek == 2) {
            strWeek = "월요";
        } else if (nWeek == 3) {
            strWeek = "화요일";
        } else if (nWeek == 4) {
            strWeek = "수요일";
        } else if (nWeek == 5) {
            strWeek = "목요일";
        } else if (nWeek == 6) {
            strWeek = "금요일";
        } else if (nWeek == 7) {
            strWeek = "토요일";
        }

        return strWeek;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        YouthCenter center = arrayList.get(position);

        holder.shelter_location_TextView.setText(center.getAddress());
        holder.shelter_name_TextView.setText(center.getName());
        if(doDayOfWeek().equals("일요일")){
            holder.shelter_playTime_TextView.setText(center.getSunday());
        }
        else if(doDayOfWeek().equals("토요일")){
            holder.shelter_playTime_TextView.setText(center.getSaturday());
        }
        else{
            holder.shelter_playTime_TextView.setText(center.getWeekday());
        }

        holder.shelter_like_TextView.setText(center.getLike()+"");


        /*holder.textView.setText(arrayList.get(position));
        if(arrayList.get(position).equals(MainActivity.drawerMneu[0])){//내정보
            holder.imageView.setImageResource(R.drawable.main_drawer_info);
        }
        else if(arrayList.get(position).equals(MainActivity.drawerMneu[1])){//공지사항
            holder.imageView.setImageResource(R.drawable.main_drawer_notice);
        }
        else if(arrayList.get(position).equals(MainActivity.drawerMneu[2])){//시장IN정보
            holder.imageView.setImageResource(R.drawable.main_drawer_sijang);
        }
        else if(arrayList.get(position).equals(MainActivity.drawerMneu[3])){//로그아웃
            holder.imageView.setImageResource(R.drawable.main_drawer_exit);
        }*/
    }

    @Override
    public FirstFragmentUserRecyclerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.data_center_recyclerview, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }
}
