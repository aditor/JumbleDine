package com.adi.jumbledine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//main area to select a bunch of options - > direct to other activities
public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }
}
