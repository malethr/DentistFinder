package com.epicodus.dentistfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.ui.DentistDetailActivity;
import com.epicodus.dentistfinder.ui.DentistDetailFragment;
import com.epicodus.dentistfinder.util.OnDentistSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistListAdapter extends RecyclerView.Adapter<DentistListAdapter.DentistViewHolder>{
    private ArrayList<Dentist> mDentists = new ArrayList<>();
    private Context mContext;
    private OnDentistSelectedListener mDentistSelectedListener;

    public DentistListAdapter(Context context, ArrayList<Dentist> dentists, OnDentistSelectedListener dentistSelectedListener) {
        mContext = context;
        mDentists = dentists;
        mDentistSelectedListener = dentistSelectedListener;
    }

    @Override
    public DentistListAdapter.DentistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dentist_list_item, parent, false);
        DentistViewHolder viewHolder = new DentistViewHolder(view, mDentists, mDentistSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DentistListAdapter.DentistViewHolder holder, int position) {
        holder.bindDentist(mDentists.get(position));
    }

    @Override
    public int getItemCount() {
        return mDentists.size();
    }

    public class DentistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.dentistImageView) ImageView mDentistImageView;
        @Bind(R.id.dentistNameTextView) TextView mDentistNameTextView;
        @Bind(R.id.streetTextView) TextView mStreetTextView;
        @Bind(R.id.phoneTextView) TextView mPhoneTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Dentist> mDentists = new ArrayList<>();
        private OnDentistSelectedListener mDentistSelectedListener;

        public DentistViewHolder(View itemView, ArrayList<Dentist> dentists, OnDentistSelectedListener dentistSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mDentists = dentists;
            mDentistSelectedListener = dentistSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }


        public void bindDentist(Dentist dentist) {
            Picasso.with(mContext).load(dentist.getImageUrl()).into(mDentistImageView);
            mDentistNameTextView.setText(dentist.getFirstName() + " " + dentist.getLastName());
            String phoneNum = TextUtils.join("",dentist.getPhone());
            phoneNum ="("+phoneNum.substring(0,3)+")" + phoneNum.substring(3,6)+"-"+ phoneNum.substring(6, phoneNum.length());
            mPhoneTextView.setText(phoneNum);
            mStreetTextView.setText(dentist.getStreet());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mDentistSelectedListener.onDentistSelected(itemPosition, mDentists, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, DentistDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_DENTISTS, Parcels.wrap(mDentists));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }

        private void createDetailFragment(int position) {
            DentistDetailFragment detailFragment = DentistDetailFragment.newInstance(mDentists, position, Constants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.dentistDetailContainer, detailFragment);
            ft.commit();
        }
    }

}
