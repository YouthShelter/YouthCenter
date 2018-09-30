package youthshelter.youth.co.kr.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import youthshelter.youth.co.kr.R;
import youthshelter.youth.co.kr.Util.GeocodeUtil;
import youthshelter.youth.co.kr.adapter.AddressListViewAdapter;
import youthshelter.youth.co.kr.item.ListViewItem;
import youthshelter.youth.co.kr.task.AddressAddTask;

public class SetMapActivity extends AppCompatActivity  {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3", "LIST4", "LIST5", "LIST6", "LIST7", "LIST8", "LIST9"};
    private EditText editText_address;
    private ImageButton findbtn;

    private ListView listview;
    private DisplayMetrics metrics;
    private AddressListViewAdapter adapter = new AddressListViewAdapter();
    private GeocodeUtil geocodeUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmap);
        geocodeUtil = new GeocodeUtil(this);
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem listViewItem = (ListViewItem) listview.getAdapter().getItem(position);
                String roadAddress = listViewItem.getRoadAddress();
                String title = listViewItem.getTitle();

                ArrayList<GeocodeUtil.GeoLocation> geoLocations = geocodeUtil.getGeoLocationListUsingAddress(roadAddress);
                if (geoLocations.size() < 1) {
                    geoLocations = geocodeUtil.getGeoLocationListUsingAddress(title);
                }
                Log.i("", geoLocations.get(0).getLatitude() + " : Latitute,==" + geoLocations.get(0).getLongitude());
            }
        });
        Resources resources = this.getResources();
        metrics = resources.getDisplayMetrics();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_setmap);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        View view = actionBar.getCustomView();

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_setmap_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // activity for result
            }
        });

        findbtn = (ImageButton) findViewById(R.id.findBtn);
        editText_address = (EditText) findViewById(R.id.edittext_address);

        editText_address.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    findbtn.callOnClick();
                    Log.i("!@#!@#!@#@!$!@$!@#!@#","!@#!@#!@#!@#!@#@@@@@@@@@");
                    return true;
                }
                Log.i("****************","^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                return false;
            }
        });

        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText_address.getWindowToken(), 0);

                adapter.clear();
                adapter.notifyDataSetChanged();

                new AddressAddTask().execute(editText_address, adapter, metrics, listview);

            }
        });

        Button nowFind = (Button) findViewById(R.id.now_location);

        nowFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
