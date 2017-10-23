package com.epicodus.dentistfinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistListAdapter extends RecyclerView.Adapter<DentistListAdapter.DentistViewHolder>{
    private ArrayList<Dentist> mDentists = new ArrayList<>();
    private Context mContext;

    public DentistListAdapter(Context context, ArrayList<Dentist> dentists) {
        mContext = context;
        mDentists = dentists;
    }

    @Override
    public DentistListAdapter.DentistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dentist_list_item, parent, false);
        DentistViewHolder viewHolder = new DentistViewHolder(view);
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

    public class DentistViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dentistImageView) ImageView mDentistImageView;
        @Bind(R.id.dentistNameTextView) TextView mDentistNameTextView;
        @Bind(R.id.streetTextView) TextView mStreetTextView;
        @Bind(R.id.websiteTextView) TextView mWebsiteTextView;

        private Context mContext;

        public DentistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDentist(Dentist dentist) {
            Picasso.with(mContext).load(dentist.getImageUrl()).into(mDentistImageView);
            mDentistNameTextView.setText(dentist.getName());
            mWebsiteTextView.setText(dentist.getWebsite());
            mStreetTextView.setText(dentist.getStreet());
        }
    }

}
