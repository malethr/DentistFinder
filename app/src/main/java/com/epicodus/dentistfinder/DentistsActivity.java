package com.epicodus.dentistfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DentistsActivity extends AppCompatActivity {
    @Bind(R.id.resultsTextView) TextView mResultsTextView;
    @Bind(R.id.dentistsListView) ListView mDentistsListView;
    private String[] dentists = new String[] {
            "Matthew Aldridge",
            "Benjamin Thomas",
            "Joshua Hiller",
            "Clinton Harrel",
            "Alexander Kussad",
            };

    private String[] address = new String []{
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
        DentistArrayAdapter adapter = new DentistArrayAdapter(this, android.R.layout.simple_list_item_1, dentists, address);
        mDentistsListView.setAdapter(adapter);
        mDentistsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dentist = ((TextView)view).getText().toString();
                Intent intent = new Intent(DentistsActivity.this, DentistDetailActivity.class);
                intent.putExtra("dentist", dentist);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String inputSearch = intent.getStringExtra("inputSearch");
        mResultsTextView.setText("Search results for " + inputSearch);

    }
}
