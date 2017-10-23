package com.epicodus.dentistfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistDetailFragment extends Fragment {
    @Bind(R.id.dentistImageView) ImageView mDentistImageView;
    @Bind(R.id.dentistNameTextView) TextView mDentistNameTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.streetTextView) TextView mStreetTextView;
    @Bind(R.id.phoneTextView) TextView mPhoneTextView;

    private Dentist mDentist;

    public static DentistDetailFragment newInstance(Dentist dentist) {
        DentistDetailFragment dentistDetailFragment = new DentistDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("dentist", Parcels.wrap(dentist));
        dentistDetailFragment.setArguments(args);
        return dentistDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDentist = Parcels.unwrap(getArguments().getParcelable("dentist"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dentist_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mDentist.getImageUrl()).into(mDentistImageView);

        mDentistNameTextView.setText(mDentist.getName());
        mStreetTextView.setText(mDentist.getStreet() + ", " + mDentist.getCity() + ", " + mDentist.getState() + ", " + mDentist.getZip());
        mWebsiteTextView.setText(mDentist.getWebsite());

        return view;
    }

}
