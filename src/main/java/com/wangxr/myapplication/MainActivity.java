package com.wangxr.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wangxr.myapplication.adapter.CallLogAdapter;
import com.wangxr.myapplication.adapter.ViewPageAdapter;
import com.wangxr.myapplication.entry.CallLogEntry;
import com.wangxr.myapplication.entry.ContactsEntry;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private Context context;

    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_call_log:
                  /*  if(callLogEntries == null){
                        callLogEntries = loadCallLog();

                    }
                    ListView callLogListView = findViewById(R.id.call_log_list_view.xml);
                    callLogListView.setAdapter(new CallLogAdapter(callLogEntries, LayoutInflater.from(context)));
*/
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_address:
//                    if(callLogEntries == null) {
//                        callLogEntries = loadCallLog();
//                    }
//                    ListView addressListView = findViewById(R.id.contacts_list_view);
//                    addressListView.setAdapter(new CallLogAdapter(callLogEntries, LayoutInflater.from(context)));
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_message:
//                    if(callLogEntries == null) {
//                        callLogEntries = loadCallLog();
//                    }
//                    ListView messageListView = findViewById(R.id.message_list_view);
//                    messageListView.setAdapter(new CallLogAdapter(callLogEntries, LayoutInflater.from(context)));
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(callLogEntries == null) {
//            callLogEntries = loadCallLog();
//        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ViewPageAdapter viewPageAdapter  = new ViewPageAdapter(getSupportFragmentManager(), navigation);
        viewPager = findViewById(R.id.vpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_call_log);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_address);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_message);
                        break;
                    default:break;

                  }

             }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setCurrentItem(0);
        context =this;
    }


}


