package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.dentistfinder.R;

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



        ButterKnife.bind(this);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mDentistFinderTextView.setTypeface(ostrichFont);

        mFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSearch = mInputEditText.getText().toString();
                if(inputSearch.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter zipcode, dentist's name or insurance!",Toast.LENGTH_SHORT).show();
                }else{
                Intent intent = new Intent(MainActivity.this, DentistsActivity.class);
                intent.putExtra("inputSearch", inputSearch);
                startActivity(intent);}
            }
        });
    }
}
