package com.sepon.regnumtollplaza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sepon.regnumtollplaza.adapter.AxelAdapter;
import com.sepon.regnumtollplaza.admin.Report;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AxelDetailsActivity extends AppCompatActivity {

    String axel;
    RecyclerView recyclerView;
    AxelAdapter axelAdapter;
    ArrayList<Report> axelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axel_details);

        Intent i = getIntent();
        axel = i.getStringExtra("axel");
        Toast.makeText(this, axel, Toast.LENGTH_SHORT).show();

        axelList = new ArrayList<>();
        axelList = getArrayList(axel);

        recyclerView = findViewById(R.id.axel_recyclerview);
        axelAdapter = new AxelAdapter(axelList, AxelDetailsActivity.this);
        recyclerView.setAdapter(axelAdapter);


    }

    public ArrayList<Report> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Report>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
