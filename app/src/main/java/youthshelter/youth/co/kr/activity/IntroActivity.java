package youthshelter.youth.co.kr.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import youthshelter.youth.co.kr.data.YouthCenter;
import youthshelter.youth.co.kr.fragment.IntroFragment;
import youthshelter.youth.co.kr.R;


public class IntroActivity extends AppCompatActivity {
    DatabaseReference mRootRef;
    DatabaseReference mPostReference;
    ArrayList<YouthCenter> youthCenters = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mPostReference = mRootRef.child("center");
        mPostReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.i("ttttt", dataSnapshot.getKey() + dataSnapshot.getChildrenCount() + "@@");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Log.i("ttttt", snapshot.getKey() + "!!");
                    YouthCenter center = snapshot.getValue(YouthCenter.class);
                    youthCenters.add(center);
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("centers", youthCenters);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_intro);
        setFragment(new IntroFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.Fragment, fragment);
        fragmentTransaction.commit();
    }
}