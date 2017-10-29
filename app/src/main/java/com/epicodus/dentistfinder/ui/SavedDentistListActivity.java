package com.epicodus.dentistfinder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;


import com.epicodus.dentistfinder.adapters.FirebaseDentistViewHolder;
import com.epicodus.dentistfinder.models.Dentist;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDentistListActivity extends AppCompatActivity {
    private DatabaseReference mDentistReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dentists);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDentistReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DENTISTS)
                .child(uid);

        mDentistReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DENTISTS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Dentist, FirebaseDentistViewHolder>
                (Dentist.class, R.layout.dentist_list_item, FirebaseDentistViewHolder.class,
                        mDentistReference) {

            @Override
            protected void populateViewHolder(FirebaseDentistViewHolder viewHolder,
                                              Dentist model, int position) {
                viewHolder.bindDentist(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}