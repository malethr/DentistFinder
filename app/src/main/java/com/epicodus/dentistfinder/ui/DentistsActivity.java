package com.epicodus.dentistfinder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.dentistfinder.R;
import com.epicodus.dentistfinder.models.Dentist;
import com.epicodus.dentistfinder.services.BetterDoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DentistsActivity extends AppCompatActivity {

    public static final String TAG = DentistsActivity.class.getSimpleName();
    @Bind(R.id.resultsTextView) TextView mResultsTextView;
    @Bind(R.id.dentistsListView) ListView mDentistsListView;

    public ArrayList<Dentist> dentists = new ArrayList<>();
    private String[] dentistsa = new String[]{
            "Matthew Aldridge",
            "Benjamin Thomas",
            "Joshua Hiller",
            "Clinton Harrel",
            "Alexander Kussad",
    };

    private String[] address = new String[]{
            "Vancouver, WA",
            "Vancouver, WA",
            "Vancouver, WA",
            "Vancouver, WA",
            "Vancouver, WA",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentists);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String inputSearch = intent.getStringExtra("inputSearch");
        mResultsTextView.setText("Search results for " + inputSearch);

        getDentists(inputSearch);
    }

    private void getDentists(String location) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();

        betterDoctorService.findDentists(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dentists = betterDoctorService.processResults(response);

                DentistsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] dentistNames = new String[dentists.size()];
                        for (int i = 0; i < dentistNames.length; i++) {
                            dentistNames[i] = dentists.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(DentistsActivity.this, android.R.layout.simple_list_item_1, dentistNames);
                        mDentistsListView.setAdapter(adapter);

                        for (Dentist dentist : dentists) {
                            Log.d(TAG, "Name: " + dentist.getName());
                            Log.d(TAG, "Phone: " + dentist.getPhone());
                            Log.d(TAG, "Website: " + dentist.getWebsite());
                            Log.d(TAG, "Image url: " + dentist.getImageUrl());
                            Log.d(TAG, "City: " + dentist.getCity());
                            Log.d(TAG, "Street: " + dentist.getStreet());
                            Log.d(TAG, "State: " + dentist.getState());
                            Log.d(TAG, "Zip: " + dentist.getZip());
                        }
                    }
                });
            }
        });
    }
}
