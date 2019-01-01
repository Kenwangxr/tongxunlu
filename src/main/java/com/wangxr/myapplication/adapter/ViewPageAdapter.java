package com.wangxr.myapplication.adapter;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wangxr.myapplication.R;
import com.wangxr.myapplication.fragment.CallLogFragment;
import com.wangxr.myapplication.fragment.ContactsFragment;
import com.wangxr.myapplication.fragment.MessageLogFragment;

import java.security.MessageDigest;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private CallLogFragment callLogFragment;

    private ContactsFragment contactsFragment;

    private MessageLogFragment messageLogFragment;

    private BottomNavigationView navigationView;

    public ViewPageAdapter(FragmentManager fm, BottomNavigationView navigationView) {
        super(fm);
        this.callLogFragment = new CallLogFragment();
        this.contactsFragment = new ContactsFragment();
        this.messageLogFragment = new MessageLogFragment();
        this.navigationView = navigationView;
    }

    @Override
    public Fragment getItem(int i) {
        System.out.println("viewPageAdapter:"+ i);
        switch (i){
            case 0:
                navigationView.setSelectedItemId(R.id.navigation_call_log);
                return callLogFragment;
            case 1:
                navigationView.setSelectedItemId(R.id.navigation_address);
                return contactsFragment;
            case 2:
                navigationView.setSelectedItemId(R.id.navigation_message);
                return messageLogFragment;
                default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
