package com.sepon.regnumtollplaza;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sepon.regnumtollplaza.main.SectionsPagerAdapter;
import com.sepon.regnumtollplaza.pojo.Norshinddi;
import com.sepon.regnumtollplaza.utility.ApiClient;
import com.sepon.regnumtollplaza.utility.IApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChorshindduActivity extends AppCompatActivity {

    TextView title;
    String plazaname;
    ImageButton backBtn;

    private List<Norshinddi> todayreport;// = new ArrayList<>();

    private ArrayList<Norshinddi> arrayList = new ArrayList<>();


    private ArrayList<JSONObject> Rickshaw_Van = new ArrayList<>();
    private ArrayList<JSONObject> MotorCycle = new ArrayList<>();
    private ArrayList<JSONObject> three_four_Wheeler = new ArrayList<>();
    private ArrayList<JSONObject> Sedan_Car = new ArrayList<>();
    private ArrayList<JSONObject> Wheeler = new ArrayList<>();
    private ArrayList<JSONObject> Micro_Bus = new ArrayList<>();
    private ArrayList<JSONObject> Mini_Bus = new ArrayList<>();
    private ArrayList<JSONObject> Agro_Use = new ArrayList<>();
    private ArrayList<JSONObject> Mini_Truck = new ArrayList<>();
    private ArrayList<JSONObject> Big_Bus = new ArrayList<>();
    private ArrayList<JSONObject> Medium_Truck = new ArrayList<>();
    private ArrayList<JSONObject> Heavy_Truck = new ArrayList<>();
    private ArrayList<JSONObject> Trailer_Long = new ArrayList<>();

    String url = "http://103.95.99.196/api/today.php";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


       // getCatalogiesProduct(url);

      ///  calculateCar();

        getdata();


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        title = findViewById(R.id.title);
        //FloatingActionButton fab = findViewById(R.id.fab);

        Intent intent = getIntent();
        plazaname = intent.getStringExtra("plazaName");
        title.setText(plazaname);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    private void getdata() {

        ProgressDialog dialog = ProgressDialog.show(this, "Getting Current Report From Server", "Please wait...", true);

        IApiClient iApiClient = ApiClient.getClient().create(IApiClient.class);
        Call<List<Norshinddi>> call =iApiClient.gettodaysreport();
        call.enqueue(new Callback<List<Norshinddi>>() {
            @Override
            public void onResponse(Call<List<Norshinddi>> call, Response<List<Norshinddi>> response) {
                int s = response.code();
                Log.e("Status", String.valueOf(s));
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Norshinddi>> call, Throwable t) {

                dialog.dismiss();
            }
        });

    }


    public void getCatalogiesProduct(String url){
        ProgressDialog dialog = ProgressDialog.show(this, "Getting Current Report From Server", "Please wait...", true);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(ChorshindduActivity.this);

       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new com.android.volley.Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               Log.e("Car: ", String.valueOf(response.length()));
               for (int i=0; i<response.length();i++){
                   try {
                      // JSONObject j = response.getJSONObject(i);
                       String o = response.getJSONObject(i).toString();
                       JsonParser parser = new JsonParser();
                       JsonElement jsonObject = parser.parse(o);
                       Gson gson = new Gson();
                       Norshinddi norshinddi = gson.fromJson(jsonObject, Norshinddi.class);
                      // Norshinddi nor = gson.fromJson(j, Norshinddi.class);
                      // Name name=gson.fromJson(jsonString,Name.class);

//                       Norshinddi norshinddi = new Norshinddi(j.getString("Agro_Use"),
//                               j.getString("amount"),
//                               j.getString("Big_Bus"),
//                               j.getString("date_time"),
//                               j.getString("Heavy_Truck"),
//                               j.getString("Medium_Truck"),
//                               j.getString("Micro_Bus"),
//                               j.getString("Mini_Bus"),
//                               j.getString("Mini_Truck"),
//                               j.getString("MotorCycle"),
//                               j.getString("pass_id"),
//                               j.getString("RegNO"),
//                               j.getString("Rickshaw_Van"),
//                               j.getString("Sedan_Car"),
//                               j.getString("three_four_Wheeler"),
//                               j.getString("Trailer_Long"),
//                               j.getString("4Wheeler"));
                       arrayList.add(norshinddi);
//                       if (j.getString("Rickshaw_Van").equals("1")){
//                           Rickshaw_Van.add(j);
//                       }else if (j.getString("MotorCycle").equals("1")){
//                           MotorCycle.add(j);
//                       }else if (j.getString("three_four_Wheeler").equals("1")){
//                           three_four_Wheeler.add(j);
//                       }else if (j.getString("Sedan_Car").equals("1")){
//                           Sedan_Car.add(j);
//                       }else if (j.getString("4Wheeler").equals("1")){
//                           Wheeler.add(j);
//                       }else if (j.getString("Micro_Bus").equals("1")){
//                           Micro_Bus.add(j);
//                       }else if (j.getString("Mini_Bus").equals("1")){
//                           Mini_Bus.add(j);
//                       }else if (j.getString("Agro_Use").equals("1")){
//                           Agro_Use.add(j);
//                       }else if (j.getString("Mini_Truck").equals("1")){
//                           Mini_Truck.add(j);
//                       }else if (j.getString("Big_Bus").equals("1")){
//                           Big_Bus.add(j);
//                       }else if (j.getString("Medium_Truck").equals("1")){
//                           Medium_Truck.add(j);
//                       }else if (j.getString("Heavy_Truck").equals("1")){
//                           Heavy_Truck.add(j);
//                       }else if (j.getString("Trailer_Long").equals("1")){
//                           Trailer_Long.add(j);
//                       }else{
//                           arrayList.add(j);
//                       }


                       //
                   }catch (Exception e){

                   }
               }

               dialog.dismiss();
           }
       }, new com.android.volley.Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {


               dialog.dismiss();
           }
       });
       requestQueue.add(jsonArrayRequest);


    }

    private void calculateCar() {
        Log.e("Arraylist: ", String.valueOf(arrayList.size()));

        for (int i=0; i<arrayList.size();i++){
           String amouhnt = arrayList.get(i).getAmount();
            Log.e("Amount", amouhnt);
        }

        Log.e("Rickshaw_Van", String.valueOf(arrayList.size()));
//        Log.e(" MotorCycle", String.valueOf(MotorCycle.size()));
//        Log.e("three_four_Wheeler ", String.valueOf(three_four_Wheeler.size()));
//        Log.e("Sedan_Car ", String.valueOf(Sedan_Car.size()));
//        Log.e("Wheeler ", String.valueOf(Wheeler.size()));
//        Log.e("Micro_Bus ", String.valueOf(Micro_Bus.size()));
//        Log.e("Mini_Bus ", String.valueOf(Mini_Bus.size()));
//        Log.e("Agro_Use ", String.valueOf(Agro_Use.size()));
//        Log.e("Mini_Truck ", String.valueOf(Mini_Truck.size()));
//        Log.e("Big_Bus ", String.valueOf(Big_Bus.size()));
//        Log.e("Medium_Truck ", String.valueOf(Medium_Truck.size()));
//        Log.e("Mini_Truck ", String.valueOf(Mini_Truck.size()));
//        Log.e("Heavy_Truck ", String.valueOf(Heavy_Truck.size()));
//        Log.e("Trailer_Long ", String.valueOf(Trailer_Long.size()));
    }

}