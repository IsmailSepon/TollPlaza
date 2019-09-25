package com.sepon.regnumtollplaza.fragment.chittagong;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.fragment.Previous_fragment;
import com.sepon.regnumtollplaza.pojo.Previous_pojo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Graph_fragment extends Fragment {

    List<SliceValue> pieData = new ArrayList<>();

    ArrayList<Previous_pojo> list2; // = new ArrayList<>();
    protected Typeface tfLight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grapg_fragment, container, false);

        list2 = new ArrayList<>();
        PieChartView pieChartView = view.findViewById(R.id.piechart);

        List pieData = new ArrayList<>();
        list2 = getArrayList("previous_report");

            for (int i=0; i<list2.size();i++){
                int value = Integer.parseInt(list2.get(i).getCtrl());
                String date = list2.get(i).getDate();
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                pieData.add(new SliceValue(value, color).setLabel(value+"/"+date));
            }

            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
           // pieChartData.setHasCenterCircle(true).setCenterText1("Chorshinddu Axel").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);



        return view;
    }

    public ArrayList<Previous_pojo> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Previous_pojo>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
