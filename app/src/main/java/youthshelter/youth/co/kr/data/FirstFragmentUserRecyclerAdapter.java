package youthshelter.youth.co.kr.data;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;


import youthshelter.youth.co.kr.R;
public class FirstFragmentUserRecyclerAdapter extends RecyclerView.Adapter<FirstFragmentUserRecyclerAdapter.UserViewHolder> {
    private ArrayList<String> arrayList;
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

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shelter_main_imageView;
        ImageView img2;
        ImageView img3;
        LinearLayout linearLayout;

        public UserViewHolder(View itemView) {
            super(itemView);
            shelter_main_imageView = (ImageView) itemView.findViewById(R.id.shelter_main_imageView);
            /*linearLayout = (LinearLayout) itemView.findViewById(R.id.user_recycler_view);
            linearLayout.setOnClickListener(this);
            linearLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    if (action == MotionEvent.ACTION_MOVE && v.isPressed()) {
                        linearLayout.setBackgroundColor(Color.parseColor("#c6c4c4"));
                    } else {

                        linearLayout.setBackgroundColor(Color.parseColor("#d8ebec"));
                    }
                    return false;
                }
            });*/
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(context, "블루투스를 검색합니다.",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(context,BluetoothConnect.class);
//            context.startActivity(intent);
           /* Bundle bundle = new Bundle();
            bundle.putInt("message",1);
            String data = textView.getText().toString();
            bundle.putString("data",data);
            Message message = new Message();
            message.setData(bundle);
            handler.sendMessage(message);*/
        }
    }

    public void addItem(String name) {
        arrayList.add(name);
        notifyItemInserted(arrayList.size()-1);//notifyItemInserted(arrayList.size()-1);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
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
