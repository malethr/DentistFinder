package com.epicodus.dentistfinder.ui;



import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.adapters.DentistPagerAdapter;
import com.epicodus.dentistfinder.models.Dentist;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private DentistPagerAdapter adapterViewPager;
    ArrayList<Dentist> mDentists = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_detail);
        ButterKnife.bind(this);

        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);
        mDentists = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_DENTISTS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new DentistPagerAdapter(getSupportFragmentManager(), mDentists, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}
