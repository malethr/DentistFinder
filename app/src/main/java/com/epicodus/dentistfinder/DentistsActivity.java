package com.epicodus.dentistfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DentistsActivity extends AppCompatActivity {
    private TextView mResultsTextView;
    private ListView mDentistsListView;
    private String[] dentists = new String[] {
            "Matthew Aldridge",
            "Benjamin Thomas",
            "Joshua Hiller",
            "Clinton Harrel",
            "Alexander Kussad",
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentists);

        mResultsTextView = (TextView) findViewById(R.id.resultsTextView);
        mDentistsListView = (ListView) findViewById(R.id.dentistsListView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dentists);
        mDentistsListView.setAdapter(adapter);
        mDentistsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dentist = ((TextView)view).getText().toString();
                Intent intent = new Intent(DentistsActivity.this, DentistDetailActivity.this);
                intent.putExtra("dentist", dentist);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String inputSearch = intent.getStringExtra("inputSearch");
        mResultsTextView.setText("Search results for " + inputSearch);

    }
}
