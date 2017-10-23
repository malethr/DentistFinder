package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.dentistfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistDetailActivity extends AppCompatActivity {
    @Bind(R.id.nameAddTextView) TextView mNameAddTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String dentist = intent.getStringExtra("dentist");
        mNameAddTextView.setText(dentist);
    }
}
