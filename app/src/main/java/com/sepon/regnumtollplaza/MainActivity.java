package com.sepon.regnumtollplaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sepon.regnumtollplaza.adapter.PlazaAdapter;
import com.sepon.regnumtollplaza.admin.AddUserActivity;
import com.sepon.regnumtollplaza.admin.ExcelReadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private GridLayoutManager mRecyclerGridMan;
    List<Plaza> plazalist = new ArrayList<>();
    PlazaAdapter mPlazaAdapter;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    String studentId, thisDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        studentId = currentUser.getUid();

        recyclerView = findViewById(R.id.Plaza_recyclerview);
        int numberOfColumns = 2;
        mRecyclerGridMan = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(mRecyclerGridMan);

        generateplaza();
    }

    private void generateplaza() {
        Plaza plaza = new Plaza(R.drawable.tollview,"Chorshinddu");
        Plaza plaza1 = new Plaza(R.drawable.tollview,"ManikGong");
        Plaza plaza3 = new Plaza(R.drawable.tollview,"Chittagong");
        plazalist.add(plaza);
        plazalist.add(plaza1);
        plazalist.add(plaza3);
        mPlazaAdapter = new PlazaAdapter(plazalist, MainActivity.this);
        recyclerView.setAdapter(mPlazaAdapter);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.boss, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
                // write your code here
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        mAuth.getInstance().signOut();
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
        MainActivity.this.finish();
    }
}
