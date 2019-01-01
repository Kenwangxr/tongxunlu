package com.wangxr.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangxr.myapplication.R;
import com.wangxr.myapplication.entry.CallLogEntry;

import java.util.List;

public class CallLogAdapter extends BaseAdapter {
    private List<CallLogEntry> callLogEntryList;

    private LayoutInflater inflater;

    public CallLogAdapter() {
    }

    public CallLogAdapter(List<CallLogEntry> callLogEntryList, LayoutInflater inflater) {
        this.callLogEntryList = callLogEntryList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return callLogEntryList.size();
    }

    @Override
    public CallLogEntry getItem(int position) {
        return callLogEntryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView callLogName;

        TextView callLogPhone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        CallLogEntry callLogEntry = getItem(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.call_log, null);
            viewHolder = new ViewHolder();
            TextView callLogNameView = (TextView)convertView.findViewById(R.id.call_log_name);
            TextView callLogPhoneView = (TextView)convertView.findViewById(R.id.call_log_phone);
            callLogNameView.setText(callLogEntry.getName());
            callLogPhoneView.setText(callLogEntry.getNumber());
            viewHolder.callLogName = callLogNameView;
            viewHolder.callLogPhone = callLogPhoneView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.callLogName.setText(callLogEntry.getName());
            viewHolder.callLogPhone.setText(callLogEntry.getNumber());

        }
        return convertView;
    }
}
