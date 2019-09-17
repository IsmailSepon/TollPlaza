package com.sepon.regnumtollplaza.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sepon.regnumtollplaza.R;

public class DelateActivity extends AppCompatActivity {

    EditText etdate;
    String date;
    Button delatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delate);

        etdate = findViewById(R.id.et_date);
        delatebtn = findViewById(R.id.delatebtn);
        delatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = etdate.getText().toString().trim();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("chittagong").child(date);
             // reference.removeValue();
              reference.removeValue(new DatabaseReference.CompletionListener() {
                  @Override
                  public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                          Toast.makeText(DelateActivity.this, "Successfully Delate", Toast.LENGTH_SHORT).show();
                  }
              });

            }
        });


    }
}
