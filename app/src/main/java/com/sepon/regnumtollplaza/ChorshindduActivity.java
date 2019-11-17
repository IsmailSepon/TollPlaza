package com.sepon.regnumtollplaza;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.sepon.regnumtollplaza.main.SectionsPagerAdapter;
import com.sepon.regnumtollplaza.pojo.Norshinddi;
import com.sepon.regnumtollplaza.utility.ApiClient;
import com.sepon.regnumtollplaza.utility.IApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class ChorshindduActivity extends AppCompatActivity {

    TextView title;
    String plazaname;
    ImageButton backBtn;

    private List<Norshinddi> todayreport;// = new ArrayList<>();
    private ArrayList<Norshinddi> arrayList = new ArrayList<>();

    String url = "http://10.10.10.3/api/yesterday.php";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        getCatalogiesProduct(url);
     //  getTodayData();


    }

    private void getTodayData() {
        ProgressDialog dialog = ProgressDialog.show(this, "Getting Current Report From Server", "Please wait...", true);

        IApiClient iapiClient =ApiClient.getClient().create(IApiClient.class);
        Call<Norshinddi> call = iapiClient.gettodaysreport();
        call.enqueue(new Callback<Norshinddi>() {
            @Override
            public void onResponse(Call<Norshinddi> call, Response<Norshinddi> response) {
                int statuscode = response.code();
                if (statuscode == 200){


                    String daat = String.valueOf(response.body());
                    Toast.makeText(ChorshindduActivity.this, "data "+daat, Toast.LENGTH_SHORT).show();


                }else {

                    Toast.makeText(ChorshindduActivity.this, "D "+ statuscode, Toast.LENGTH_SHORT).show();
                }


                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Norshinddi> call, Throwable t) {

                dialog.dismiss();
            }
        });

    }


    public void getCatalogiesProduct(String url){
        ProgressDialog dialog = ProgressDialog.show(this, "Getting Current Report From Server", "Please wait...", true);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array =  response.getJSONArray("data");
                            if (array.length() == 0){
                                Toast.makeText(getApplicationContext(), "NO data found", Toast.LENGTH_SHORT).show();
//                                catagoryableRecyclerView.setVisibility(View.INVISIBLE);
//                                nestedScrollView.setVisibility(View.VISIBLE);
                            }else {
                                for (int i=0; i< array.length(); i++){
                                    Gson gson = new Gson();
                                    Norshinddi productData = gson.fromJson(array.getJSONObject(i).toString(), Norshinddi.class);
                                    arrayList.add(productData);
                                    Toast.makeText(getApplicationContext(), arrayList.size(), Toast.LENGTH_SHORT).show();
                                    Log.e("data :", String.valueOf(arrayList.size()));

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // catagory_product_adapter = new Catagory_Product_Adapter(arrayList, getContext());
                       // catagoryableRecyclerView.setAdapter(catagory_product_adapter);
                        dialog.dismiss();
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        });
        requestQueue.add(request);

    }

}