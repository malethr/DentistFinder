package com.epicodus.dentistfinder.ui;



import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_detail);
        ButterKnife.bind(this);

        mDentists = Parcels.unwrap(getIntent().getParcelableExtra("dentists"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new DentistPagerAdapter(getSupportFragmentManager(), mDentists);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}
