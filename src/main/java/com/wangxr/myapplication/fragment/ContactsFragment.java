package com.wangxr.myapplication.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
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
import com.wangxr.myapplication.adapter.ContactsAdapter;
import com.wangxr.myapplication.entry.CallLogEntry;
import com.wangxr.myapplication.entry.ContactsEntry;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private List<ContactsEntry> contactsEntries;

    public ContactsFragment() {
    }

    private List<ContactsEntry> loadContacts(Context context){
        List<ContactsEntry> contactsEntries = new ArrayList<>();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 5000);
        }
        Cursor cs = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_URI},null, null, null);
        while (cs != null && cs.getCount()!= 0&&cs.moveToNext()){
            String nickname = cs.getString(0);
//            String phone = cs.getString(1);
            String iconUri = cs.getString(1);
            ContactsEntry contactsEntry = new ContactsEntry();
            contactsEntry.setNickname(nickname);
//            contactsEntry.setPhone(phone);
            contactsEntry.setLogo(iconUri);
            contactsEntries.add(contactsEntry);
        }
        System.out.println(contactsEntries);
        return contactsEntries;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_log_list_view, container, false);
        contactsEntries = loadContacts(view.getContext());
        ListView callLogListView = view.findViewById(R.id.contacts_list_view);
        callLogListView.setAdapter(new ContactsAdapter(contactsEntries, inflater));

        return view;
    }
}
