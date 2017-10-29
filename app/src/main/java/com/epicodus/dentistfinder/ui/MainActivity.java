package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.findButton) Button mFindButton;
    @Bind(R.id.inputEditText) EditText mInputEditText;
    @Bind(R.id.dentistFinderTextView) TextView mDentistFinderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mDentistFinderTextView.setTypeface(ostrichFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindButton.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            String inputSearch = mInputEditText.getText().toString();

            saveInputSearchToFirebase(inputSearch);
//            if(!inputSearch.isEmpty()){
//                //Toast.makeText(MainActivity.this,"Enter zipcode, dentist's name or insurance!",Toast.LENGTH_SHORT).show();
//                addToSharedPreferences(inputSearch);
//            }

                Intent intent = new Intent(MainActivity.this, DentistListActivity.class);
                intent.putExtra("inputSearch", inputSearch);
                startActivity(intent);
        }

    public void saveInputSearchToFirebase(String inputSearch) {
        mSearchedLocationReference.push().setValue(inputSearch);
    }


//    private void addToSharedPreferences(String inputSearch) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, inputSearch).apply();
//    }
}
