package com.wangxr.myapplication.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wangxr.myapplication.R;
import com.wangxr.myapplication.adapter.CallLogAdapter;
import com.wangxr.myapplication.entry.CallLogEntry;

import java.util.ArrayList;
import java.util.List;

public class MessageLogFragment extends Fragment {

    private List<CallLogEntry> callLogEntries;

    public MessageLogFragment() {
    }

    private List<CallLogEntry> loadCallLog(Context context){
        List<CallLogEntry> callLogEntries = new ArrayList<>();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.READ_SMS}, 5000);
        }
        Cursor cs = context.getContentResolver().query(Telephony.Sms.CONTENT_URI, new String[]{Telephony.Sms.CREATOR,
                Telephony.Sms.BODY, Telephony.Sms.DATE},null, null, CallLog.Calls.DATE);
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

    public void setCallLogEntries(List<CallLogEntry> callLogEntries) {
        this.callLogEntries = callLogEntries;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_log_list_view, container, false);
        callLogEntries = loadCallLog(view.getContext());
        ListView callLogListView = view.findViewById(R.id.contacts_list_view);
        callLogListView.setAdapter(new CallLogAdapter(callLogEntries, inflater));

        return view;
    }
}
