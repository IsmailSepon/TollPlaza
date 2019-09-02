package com.sepon.regnumtollplaza.fragment.chittagong;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.admin.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Today_Chittagong_fragment extends Fragment {

    RecyclerView recyclerView;
    CardView card2,card3,card4,card5,card6,card7;
    private TextView r1,r2,r3,r4,r5,r6,cr1,cr2,cr3,cr4,cr5,cr6;
    private String a2,a3,a4,a5,a6,a7,at;
    private String c2,c3,c4,c5,c6,c7,ct;

    private String thisDate;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.today_chittagong_fragment, container, false);

        initilize(view);

        getDate();

        isTodayReportAvillable();

        return view;
    }

    private void isTodayReportAvillable() {

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chittagong").child(thisDate);
        Dialog dialog = ProgressDialog.show(getActivity(), "Checking todays report", "Please wait...", true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    // Toast.makeText(getActivity(), "report  update yet", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getRegularReportFromFirebase();
                        }
                    }).start();

                    getCtrlRdata();

                }else {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "report not update yet", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
    }

    private void getRegularReportFromFirebase() {
         ArrayList<Report> regularReport2 = new ArrayList<>();
         ArrayList<Report> regularReport3 = new ArrayList<>();
         ArrayList<Report> regularReport4 = new ArrayList<>();
         ArrayList<Report> regularReport5 = new ArrayList<>();
         ArrayList<Report> regularReport6 = new ArrayList<>();
         ArrayList<Report> regularReport7 = new ArrayList<>();
         ArrayList<Report> totallaxel = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("chittagong").child(thisDate).child("RegularReport");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Report report = singleSnapshot.getValue(Report.class);
                    String tc = report.getTcClass();
                    //Truck 2 Axle
                    if (tc.equals("Truck 2 Axle")){
                        regularReport2.add(report);
                    }else if (tc.equals("Truck 3 Axle")){
                        regularReport3.add(report);
                    }else if (tc.equals("Truck 4 Axle")){
                        regularReport4.add(report);
                    }else if (tc.equals("Truck 5 Axle")){
                        regularReport5.add(report);
                    }else if (tc.equals("Truck 6 Axle")){
                        regularReport6.add(report);
                    }else if (tc.equals("Truck 7 Axle")){
                        regularReport7.add(report);
                    }else {

                    }
                    totallaxel.add(report);

                }
                at = String.valueOf(totallaxel.size());
                a2 = String.valueOf(regularReport2.size());
                a3 = String.valueOf(regularReport3.size());
                a4 = String.valueOf(regularReport4.size());
                a5 = String.valueOf(regularReport5.size());
                a6 = String.valueOf(regularReport6.size());
                a7 = String.valueOf(regularReport7.size());

                Log.e("totaltr", at);
                //toptext.setText("Total Axel Report: "+String.valueOf(regularReport.size()));

                Log.e("axel2", a2);
                r1.setText("Regular : "+a2);

                Log.e("axel3", a3);
                r2.setText("Regular : "+a3);

                Log.e("axel4", a4);
                r3.setText("Regular : "+a4);

                Log.e("axel5", a5);
                r4.setText("Regular : "+a5);

                Log.e("axel6", a6);
                r5.setText("Regular : "+a6);

                Log.e("axel7", a7);
                r6.setText("Regular : "+a7);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void getCtrlRdata() {
         ArrayList<Report> ctrlReport2 = new ArrayList<>();
         ArrayList<Report> ctrlReport3 = new ArrayList<>();
         ArrayList<Report> ctrlReport4 = new ArrayList<>();
         ArrayList<Report> ctrlReport5 = new ArrayList<>();
         ArrayList<Report> ctrlReport6 = new ArrayList<>();
         ArrayList<Report> ctrlReport7 = new ArrayList<>();

        Dialog dialog = ProgressDialog.show(getActivity(), "Getting Ctrl+R Report From Server", "Please wait...", true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("chittagong").child(thisDate).child("ctrlReport");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Report report = singleSnapshot.getValue(Report.class);
                    String tc = report.getTcClass();
                    //Truck 2 Axle
                    if (tc.equals("Truck 2 Axle")){
                        ctrlReport2.add(report);
                    }else if (tc.equals("Truck 3 Axle")){
                        ctrlReport3.add(report);
                    }else if (tc.equals("Truck 4 Axle")){
                        ctrlReport4.add(report);
                    }else if (tc.equals("Truck 5 Axle")){
                        ctrlReport5.add(report);
                    }else if (tc.equals("Truck 6 Axle")){
                        ctrlReport5.add(report);
                    }else if (tc.equals("Truck 7 Axle")){
                        ctrlReport7.add(report);
                    }else {


                    }

                    //ctrlReport.add(report);

                }
                //Log.e("totaltr", String.valueOf(ctrlReport.size()));
                //toptext.setText("Total ctrl+R Axel Report: "+String.valueOf(ctrlReport.size()));
                c2  = String.valueOf(ctrlReport2.size());
                Log.e("axel2", c2);
                cr1.setText("Ctrl+R  : "+c2);

                c3 = String.valueOf(ctrlReport3.size());
                Log.e("axel3", c3);
                cr2.setText("Ctrl+R  : "+c3);

                c4 = String.valueOf(ctrlReport4.size());
                Log.e("axel4", c4);
                cr3.setText("Ctrl+R  : "+c4);

                c5 = String.valueOf(ctrlReport5.size());
                Log.e("axel5", c5);
                cr4.setText("Ctrl+R  : "+c5);

                c6 = String.valueOf(ctrlReport6.size());
                Log.e("axel6", c6);
                cr5.setText("Ctrl+R  : "+c6);

                c7 = String.valueOf(ctrlReport7.size());
                Log.e("axel7", c7);
                cr6.setText("Ctrl+R  : "+c7);

                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void getDate() {

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);
    }

    private void initilize(View view) {

        r1 = view.findViewById(R.id.axel2amount);
        r2 = view.findViewById(R.id.axel3amount);
        r3 = view.findViewById(R.id.axel4amount);
        r4 = view.findViewById(R.id.axel5amount);
        r5 = view.findViewById(R.id.axel6amount);
        r6 = view.findViewById(R.id.axel7amount);


        cr1 = view.findViewById(R.id.axectr2Ramount);
        cr2 = view.findViewById(R.id.axectrl3Ramount);
        cr3 = view.findViewById(R.id.axectrl4Ramount);
        cr4 = view.findViewById(R.id.axectrl5Ramount);
        cr5 = view.findViewById(R.id.axectrl6Ramount);
        cr6 = view.findViewById(R.id.axectrl7Ramount);
    }



}
