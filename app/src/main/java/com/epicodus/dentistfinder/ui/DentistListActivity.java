package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.util.OnDentistSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DentistListActivity extends AppCompatActivity implements OnDentistSelectedListener{
    private Integer mPosition;
    ArrayList<Dentist> mDentists;
    String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentists);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mDentists = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_DENTISTS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mDentists != null) {
                    Intent intent = new Intent(this, DentistDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_DENTISTS, Parcels.wrap(mDentists));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }

            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mDentists != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_DENTISTS, Parcels.wrap(mDentists));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }

    @Override
    public void onDentistSelected(Integer position, ArrayList<Dentist> dentists, String source) {
        mPosition = position;
        mDentists = dentists;
        mSource = source;
    }

}
