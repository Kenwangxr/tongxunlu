package com.wangxr.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.CallLog;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context context;

    public List<CallLogEntry> callLogEntries;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(callLogEntries == null) {
//            callLogEntries = loadCallLog();
//        }

        ViewPageAdapter viewPageAdapter  = new ViewPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.vpager);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setCurrentItem(0);
        context =this;
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private List<CallLogEntry> loadCallLog(){
        List<CallLogEntry> callLogEntries = new ArrayList<>();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 5000);
        }
        Cursor cs = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER, CallLog.Calls.DATE},null, null, CallLog.Calls.DATE);
        while (cs != null && cs.getCount()!= 0&&cs.moveToNext()){
            String name = cs.getString(0);
            String number = cs.getString(1);
            long date = cs.getLong(2);
            CallLogEntry callLogEntry = new CallLogEntry();
            callLogEntry.setName(name);
            callLogEntry.setNumber(number);
            callLogEntry.setDate(date);
            callLogEntries.add(callLogEntry);
        }
        System.out.println(callLogEntries);
        return callLogEntries;
    }
}


