package com.sepon.regnumtollplaza.fragment.chittagong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;
import com.sepon.regnumtollplaza.R;

public class Regular_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.regular_axel_layout, container, false);



        return view;
    }

}
