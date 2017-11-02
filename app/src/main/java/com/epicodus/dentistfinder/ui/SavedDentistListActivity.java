package com.epicodus.dentistfinder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;


import com.epicodus.dentistfinder.adapters.FirebaseDentistListAdapter;
import com.epicodus.dentistfinder.adapters.FirebaseDentistViewHolder;
import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.util.OnStartDragListener;
import com.epicodus.dentistfinder.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDentistListActivity extends AppCompatActivity implements OnStartDragListener  {
    private DatabaseReference mDentistReference;
    private FirebaseDentistListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dentists);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDentistReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DENTISTS)
                .child(uid);

        mFirebaseAdapter = new FirebaseDentistListAdapter(Dentist.class, R.layout.dentist_list_item_drag, FirebaseDentistViewHolder.class,
                mDentistReference, this, this);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);

            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
            mItemTouchHelper =new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}