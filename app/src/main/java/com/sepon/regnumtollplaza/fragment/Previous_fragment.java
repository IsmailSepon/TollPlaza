package com.sepon.regnumtollplaza.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.adapter.PreviousAdapter;
import com.sepon.regnumtollplaza.pojo.PreviousDetails;

import java.util.ArrayList;
import java.util.List;


public class Previous_fragment extends Fragment {

    PreviousAdapter previousAdapter;
    RecyclerView previousRecyclerview;
    List<PreviousDetails> previousDetailsList = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.previous_fragment, container, false);

        previousRecyclerview = view.findViewById(R.id.previous_recyclerview);
        generateprevious();

//        GraphView graph = view.findViewById(R.id.graph);
//        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<DataPoint>(new DataPoint[]{
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6),
//                new DataPoint(5, 7),
//                new DataPoint(6, 66),
//                new DataPoint(7, 90),
//                new DataPoint(8, 9),
//                new DataPoint(9, 69),
//                new DataPoint(10, 63),
//                new DataPoint(11, 16),
//                new DataPoint(12, 26)
//        });
//        series1.setDrawValuesOnTop(true);
//        series1.setValuesOnTopColor(Color.RED);
//        series1.setSpacing(20);
//        series1.setColor(Color.RED);
//        series1.getDataWidth();
//        graph.addSeries(series1);
        return view;
    }

    private void generateprevious() {
        PreviousDetails previousDetails1 = new PreviousDetails("22 September 2019", "200000","3487488 tk");
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);
        previousDetailsList.add(previousDetails1);

        previousAdapter = new PreviousAdapter(previousDetailsList, getContext());
        previousRecyclerview.setAdapter(previousAdapter);
    }
}
