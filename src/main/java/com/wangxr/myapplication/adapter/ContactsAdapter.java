package com.wangxr.myapplication.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangxr.myapplication.R;
import com.wangxr.myapplication.entry.ContactsEntry;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private List<ContactsEntry> contactsEntries;

    private LayoutInflater layoutInflater;

    public ContactsAdapter(List<ContactsEntry> contactsEntries, LayoutInflater layoutInflater) {
        this.contactsEntries = contactsEntries;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return contactsEntries.size();
    }

    @Override
    public ContactsEntry getItem(int position) {
        return contactsEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.contacts, parent, false);
            ImageView logo = convertView.findViewById(R.id.logo);
            TextView nickname = convertView.findViewById(R.id.nickname);
//            TextView phone = convertView.findViewById(R.id.phone);
            ContactsEntry contactsEntry = contactsEntries.get(position);
//            logo.setImageURI( Uri.parse(contactsEntry.getLogo()));
            nickname.setText(contactsEntry.getNickname());
//            phone.setText(contactsEntry.getPhone());
            return convertView;
//        }

//        return null;
    }
}
