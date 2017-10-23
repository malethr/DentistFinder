package com.epicodus.dentistfinder.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class DentistArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mDentists;
    private String[] mAddress;

    public DentistArrayAdapter(Context mContext, int resource, String[] mDentists, String[] mAddress) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mDentists = mDentists;
        this.mAddress = mAddress;
    }

    @Override
    public Object getItem(int position) {
        String dentist = mDentists[position];
        String address = mAddress[position];
        return String.format("Name: %s \nAddress: %s", dentist, address);
    }

    @Override
    public int getCount() {
        return mDentists.length;
    }

}
