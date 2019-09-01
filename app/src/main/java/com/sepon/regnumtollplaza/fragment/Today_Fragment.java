package com.sepon.regnumtollplaza.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.adapter.TodayAdapter;
import com.sepon.regnumtollplaza.pojo.Tali;

import java.util.ArrayList;
import java.util.List;


public class Today_Fragment extends Fragment {


    private List<Tali> taliList = new ArrayList<>();
    TodayAdapter taliAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tali_layout, container, false);

        Tali tali = new Tali("rickshaw","20","100");
        Tali tali1 = new Tali("saden","20","100");
        Tali tali2 = new Tali("minibus","20","100");
        Tali tali3 = new Tali("microbus","20","100");
        Tali tali4 = new Tali("cng","20","100");
        Tali tali5 = new Tali("bike","20","100");
        taliList.add(tali);
        taliList.add(tali1);
        taliList.add(tali2);
        taliList.add(tali3);
        taliList.add(tali4);
        taliList.add(tali5);

        recyclerView = view.findViewById(R.id.tali_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        TodayAdapter taliAdapter = new TodayAdapter(taliList, getActivity());
        recyclerView.setAdapter(taliAdapter);


        return view;
    }


}
