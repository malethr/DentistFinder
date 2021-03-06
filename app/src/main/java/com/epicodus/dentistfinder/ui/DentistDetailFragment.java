package com.epicodus.dentistfinder.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.dentistImageView) ImageView mDentistImageView;
    @Bind(R.id.dentistNameTextView) TextView mDentistNameTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.addressTextView) TextView mStreetTextView;
    @Bind(R.id.callImageView) ImageView mCallImageView;
    @Bind(R.id.saveDentistButton) TextView mSaveDentistButton;
    @Bind(R.id.bioTextView) TextView mBioTextView;

    private Dentist mDentist;
    private ArrayList<Dentist> mDentists;
    private int mPosition;
    private String mSource;

    public static DentistDetailFragment newInstance(ArrayList<Dentist> dentists, Integer position, String source) {
        DentistDetailFragment dentistDetailFragment = new DentistDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_DENTISTS, Parcels.wrap(dentists));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);

        dentistDetailFragment.setArguments(args);
        return dentistDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDentists = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_DENTISTS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mDentist = mDentists.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dentist_detail, container, false);
        ButterKnife.bind(this, view);

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveDentistButton.setVisibility(View.GONE);
        } else {
            // This line of code should already exist. Make sure it now resides in this conditional:
            mSaveDentistButton.setOnClickListener(this);
        }

        Picasso.with(view.getContext())
                .load(mDentist.getImageUrl())
                .into(mDentistImageView);

        mDentistNameTextView.setText(mDentist.getFirstName() + " " + mDentist.getLastName());
        mStreetTextView.setText(mDentist.getStreet() + ", " + mDentist.getCity() + ", " + mDentist.getState() + ", " + mDentist.getZip());

        mWebsiteTextView.setText(mDentist.getWebsite()+"");
        String bio = new String (mDentist.getBio()+"");
        String emptyBio = new String ("");
        if(bio.equals(emptyBio)){
            bio = "Sorry! No available information!";
        }

        mBioTextView.setText(bio + "");
        String web = new String("Website: unavailable");
        String website = new String (mDentist.getWebsite()+"");
        if (!website.equals(web)) {
            mWebsiteTextView.setOnClickListener(this);
        }

        mSaveDentistButton.setOnClickListener(this);
        mCallImageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mDentist.getWebsite()));
            startActivity(webIntent);
        }

        if (v == mCallImageView) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + mDentist.getPhone()));
            startActivity(phoneIntent);
        }

        if (v == mSaveDentistButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference dentistRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DENTISTS)
                    .child(uid);


            DatabaseReference pushRef = dentistRef.push();
            String pushId = pushRef.getKey();
            mDentist.setPushId(pushId);
            pushRef.setValue(mDentist);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
