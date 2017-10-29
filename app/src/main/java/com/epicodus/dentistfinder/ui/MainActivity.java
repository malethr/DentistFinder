package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.epicodus.dentistfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.dentistFinderTextView) TextView mDentistFinderTextView;
    @Bind(R.id.savedDentistsButton) Button mSavedDentistsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mDentistFinderTextView.setTypeface(ostrichFont);

        mFindButton.setOnClickListener(this);
        mSavedDentistsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindButton){
            Intent intent = new Intent(MainActivity.this, DentistListActivity.class);
            startActivity(intent);
        }
        if (v == mSavedDentistsButton) {
            Intent intent = new Intent(MainActivity.this, SavedDentistListActivity.class);
            startActivity(intent);
        }
    }
}
