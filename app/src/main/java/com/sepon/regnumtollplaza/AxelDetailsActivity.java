package com.sepon.regnumtollplaza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AxelDetailsActivity extends AppCompatActivity {

    String axel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axel_details);

        Intent i = getIntent();
        axel = i.getStringExtra("axel");
        Toast.makeText(this, axel, Toast.LENGTH_SHORT).show();

    }
}
