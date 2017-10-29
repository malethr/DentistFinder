package com.epicodus.dentistfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.ui.DentistDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseDentistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseDentistViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindDentist(Dentist dentist) {
        ImageView dentistImageView = (ImageView) mView.findViewById(R.id.dentistImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.dentistNameTextView);
        TextView streetTextView = (TextView) mView.findViewById(R.id.streetTextView);
        TextView phoneTextView = (TextView) mView.findViewById(R.id.phoneTextView);

        Picasso.with(mContext).load(dentist.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(dentistImageView);

        nameTextView.setText(dentist.getFirstName());
        String phoneNum = TextUtils.join("",dentist.getPhone());
        phoneNum ="("+phoneNum.substring(0,3)+")" + phoneNum.substring(3,6)+"-"+ phoneNum.substring(6, phoneNum.length());
        phoneTextView.setText(phoneNum);
        streetTextView.setText(dentist.getStreet());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Dentist> dentists = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DENTISTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    dentists.add(snapshot.getValue(Dentist.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DentistDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("dentists", Parcels.wrap(dentists));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

