package com.wangxr.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wangxr.myapplication.fragment.CallLogFragment;
import com.wangxr.myapplication.fragment.ContactsFragment;
import com.wangxr.myapplication.fragment.MessageLogFragment;

import java.security.MessageDigest;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private CallLogFragment callLogFragment;

    private ContactsFragment contactsFragment;

    private MessageLogFragment messageLogFragment;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        callLogFragment = new CallLogFragment();
        contactsFragment = new ContactsFragment();
        messageLogFragment = new MessageLogFragment();
    }

    @Override
    public Fragment getItem(int i) {
        System.out.println("viewPageAdapter:"+ i);
        switch (i){
            case 0:
                return callLogFragment;
            case 1:
                return contactsFragment;
            case 2:
                return messageLogFragment;
                default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
