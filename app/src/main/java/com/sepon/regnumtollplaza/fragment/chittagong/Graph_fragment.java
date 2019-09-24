package com.sepon.regnumtollplaza.fragment.chittagong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sepon.regnumtollplaza.R;

import java.util.ArrayList;


public class Graph_fragment extends Fragment {

    ArrayList<String> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grapg_fragment, container, false);

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2, 1),
                new DataPoint(3, 5),
                new DataPoint(4, 3),
                new DataPoint(5, 2),
                new DataPoint(6, 6),

                
                new DataPoint(7, 6)
        });

        graph.addSeries(series);


        Button btn = view.findViewById(R.id.insert);
        EditText rt = view.findViewById(R.id.get);
        TextView tx = view.findViewById(R.id.text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = String.valueOf(rt.getText());
                list = new ArrayList<>();
                list.add(s+" new "+s);

                tx.setText(list.get(0)+" size="+list.size());

            }
        });


        return view;
    }




}
