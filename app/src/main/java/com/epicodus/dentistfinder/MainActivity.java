package com.epicodus.dentistfinder;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mFindButton;
    private EditText mInputEditText;
    private TextView mDentistFinderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        mFindButton = (Button) findViewById(R.id.findButton);
        mDentistFinderTextView = (TextView) findViewById(R.id.dentistFinderTextView);
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
