package com.sepon.regnumtollplaza.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sepon.regnumtollplaza.ChorshindduActivity;
import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.adapter.TodayAdapter;
import com.sepon.regnumtollplaza.pojo.Norshinddi;
import com.sepon.regnumtollplaza.pojo.Tali;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Today_Fragment extends Fragment {


    private List<Tali> taliList = new ArrayList<>();
    TodayAdapter taliAdapter;
    RecyclerView recyclerView;
    String url = "http://103.95.99.196/api/today.php";

    private List<Norshinddi> todayreport;// = new ArrayList<>();

    ArrayList<Norshinddi> arrayList;// = new ArrayList<>();


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


        getDaysReport(url);

        return view;
    }


    private void getDaysReport(String url) {
     //   arrayList = new ArrayList<>();
        todayreport = new ArrayList<>();
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Getting Current Report From Server", "Please wait...", true);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Reponse", String.valueOf(response.length()));

                for (int i=0; i<response.length();i++){
                    try {

                        JSONObject j =  response.getJSONObject(i);
                        //  Log.e("Amount", j.getString("amount"));
                        Norshinddi norshinddi = new Norshinddi(j.getString("Agro_Use"),
                                j.getString("amount"),
                                j.getString("Big_Bus"),
                                j.getString("date_time"),
                                j.getString("Heavy_Truck"),
                                j.getString("Medium_Truck"),
                                j.getString("Micro_Bus"),
                                j.getString("Mini_Bus"),
                                j.getString("Mini_Truck"),
                                j.getString("MotorCycle"),
                                j.getString("pass_id"),
                                j.getString("RegNO"),
                                j.getString("Rickshaw_Van"),
                                j.getString("Sedan_Car"),
                                j.getString("three_four_Wheeler"),
                                j.getString("Trailer_Long"),
                                j.getString("4Wheeler"));
                        todayreport.add(norshinddi);

                    }catch (Exception p){

                    }
                }

             //   Log.e("List Today ", String.valueOf(todayreport.size()));
                serializeData(todayreport);
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void serializeData(List<Norshinddi> todayreport) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Serialize Data ", "Please wait...", true);

        ArrayList<Norshinddi> Rickshaw_Van = new ArrayList<>();
        ArrayList<Norshinddi> MotorCycle = new ArrayList<>();
        ArrayList<Norshinddi> three_four_Wheeler = new ArrayList<>();
        ArrayList<Norshinddi> Sedan_Car = new ArrayList<>();
        ArrayList<Norshinddi> Wheeler = new ArrayList<>();
        ArrayList<Norshinddi> Micro_Bus = new ArrayList<>();
        ArrayList<Norshinddi> Mini_Bus = new ArrayList<>();
        ArrayList<Norshinddi> Agro_Use = new ArrayList<>();
        ArrayList<Norshinddi> Mini_Truck = new ArrayList<>();
        ArrayList<Norshinddi> Big_Bus = new ArrayList<>();
        ArrayList<Norshinddi> Medium_Truck = new ArrayList<>();
        ArrayList<Norshinddi> Heavy_Truck = new ArrayList<>();
        ArrayList<Norshinddi> Trailer_Long = new ArrayList<>();
        ArrayList<Norshinddi> VIP = new ArrayList<>();



        Log.e("LIst :  ", String.valueOf(todayreport.size()));
        for (int i=0; i<todayreport.size();i++){

            if (todayreport.get(i).getMicroBus().equals("1")){
                Micro_Bus.add(todayreport.get(i));

            }else if (todayreport.get(i).getAgroUse().equals("1")){
                Agro_Use.add(todayreport.get(i));

            }else if (todayreport.get(i).getBigBus().equals("1")){
                Big_Bus.add(todayreport.get(i));

            }else if (todayreport.get(i).getHeavyTruck().equals("1")){
                Heavy_Truck.add(todayreport.get(i));

            }else if (todayreport.get(i).getMediumTruck().equals("1")){
                Medium_Truck.add(todayreport.get(i));

            }else if (todayreport.get(i).getMiniBus().equals("1")){
                Mini_Bus.add(todayreport.get(i));

            }else if (todayreport.get(i).getMiniTruck().equals("1")){
                Mini_Truck.add(todayreport.get(i));

            }else if (todayreport.get(i).getMotorCycle().equals("1")){
                MotorCycle.add(todayreport.get(i));

            }else if (todayreport.get(i).getRickshawVan().equals("1")){
                Rickshaw_Van.add(todayreport.get(i));

            }else if (todayreport.get(i).getSedanCar().equals("1")){
                Sedan_Car.add(todayreport.get(i));

            }else if (todayreport.get(i).getThreeFourWheeler().equals("1")){
                three_four_Wheeler.add(todayreport.get(i));

            }else if (todayreport.get(i).getTrailerLong().equals("1")){
                Trailer_Long.add(todayreport.get(i));

            }else if (todayreport.get(i).getWheeler().equals("1")){
                Wheeler.add(todayreport.get(i));

            }else {
                VIP.add(todayreport.get(i));
              //  Log.e("NUll ","error" );
            }
        }


        Log.e("Rickshaw_Van", String.valueOf(Rickshaw_Van.size()));
        Log.e(" MotorCycle", String.valueOf(MotorCycle.size()));
        Log.e("Wheeler ", String.valueOf(Wheeler.size()));
        Log.e("Micro_Bus ", String.valueOf(Micro_Bus.size()));
        Log.e("Mini_Bus ", String.valueOf(Mini_Bus.size()));
        Log.e("Agro_Use ", String.valueOf(Agro_Use.size()));
        Log.e("Mini_Truck ", String.valueOf(Mini_Truck.size()));
        Log.e("Big_Bus ", String.valueOf(Big_Bus.size()));
        Log.e("three_four_Wheeler ", String.valueOf(three_four_Wheeler.size()));
        Log.e("Sedan_Car ", String.valueOf(Sedan_Car.size()));
        Log.e("Medium_Truck ", String.valueOf(Medium_Truck.size()));
        Log.e("Mini_Truck ", String.valueOf(Mini_Truck.size()));
        Log.e("Heavy_Truck ", String.valueOf(Heavy_Truck.size()));
        Log.e("Trailer_Long ", String.valueOf(Trailer_Long.size()));
        Log.e("Trailer_Long ", String.valueOf(VIP.size()));
        dialog.dismiss();
    }


}
