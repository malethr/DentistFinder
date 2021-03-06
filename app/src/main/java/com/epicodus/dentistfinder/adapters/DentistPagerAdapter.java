package com.epicodus.dentistfinder.adapters;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.ui.DentistDetailFragment;


import java.util.ArrayList;

public class DentistPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Dentist> mDentists;
    private String mSource;

    public DentistPagerAdapter(FragmentManager fm, ArrayList<Dentist> dentists, String source) {
        super(fm);
        mDentists = dentists;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return DentistDetailFragment.newInstance(mDentists, position, mSource);
    }

    @Override
    public int getCount() {
        return mDentists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDentists.get(position).getFirstName() + " " + mDentists.get(position).getLastName() + ", DDS";
    }

}
