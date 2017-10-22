package com.epicodus.dentistfinder;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.inputEditText) EditText mInputEditText;
    @Bind(R.id.dentistFinderTextView) TextView mDentistFinderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "9e18d0234fa6476718599583032121ca";
        String dentistUrl = "https://api.betterdoctor.com/2016-03-01/doctors?query=${userInput}&location=wa-vancouver&sort=best-match-asc&skip=0&limit=50&user_key=9e18d0234fa6476718599583032121ca";

        ButterKnife.bind(this);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mDentistFinderTextView.setTypeface(ostrichFont);
        mFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSearch = mInputEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, DentistsActivity.class);
                intent.putExtra("inputSearch", inputSearch);
                startActivity(intent);
            }
        });
    }
}
