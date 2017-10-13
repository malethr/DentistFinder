package com.epicodus.dentistfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
        String address = intent.getStringExtra("address");
        mNameAddTextView.setText(dentist);
    }
}
