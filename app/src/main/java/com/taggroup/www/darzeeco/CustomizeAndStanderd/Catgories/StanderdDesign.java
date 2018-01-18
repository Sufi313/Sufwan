package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

import android.app.Activity;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.taggroup.www.darzeeco.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StanderdDesign extends AppCompatActivity {


    private Context context;
    private Activity activity;
    public static final String EXTRA_ID = "size_id";
    public static final String EXTRA_SIZE_NAME = "user_size_name";

    private TextView all, shirts, trouser, compelete;

    List<StanderdProduct> standerdProductsList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standerd_design);

        context = this;

        Toolbar myToolBar = (Toolbar) findViewById(R.id.standerdDesignToolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        all = (TextView) findViewById(R.id.ctgAll);
        shirts = (TextView) findViewById(R.id.ctgShirts);
        trouser = (TextView) findViewById(R.id.ctgPaints);
        compelete = (TextView) findViewById(R.id.ctgCompleteSuits);


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadProducts("http://192.168.2.41/darzee/Api.php");
            }
        });

        shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadProducts("http://192.168.2.41/darzee/Api2.php?apicall=Shirts");
            }
        });

        trouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProducts("http://192.168.2.41/darzee/Api2.php?apicall=Trousers");
            }
        });

        compelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadProducts("http://192.168.2.41/darzee/Api2.php?apicall=Complete");
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.standerd_product_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        standerdProductsList = new ArrayList<>();

//        final Intent intent = getIntent();
//        final int design_id = intent.getIntExtra(EXTRA_ID,0);
//        final String sizeName = intent.getStringExtra(EXTRA_SIZE_NAME);


    }

    private void loadProducts(String url) {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject standerdProduct = array.getJSONObject(i);

                                //adding the product to product list
                                standerdProductsList.add(new StanderdProduct(
                                        standerdProduct.getInt("id"),
                                        standerdProduct.getString("title"),
                                        standerdProduct.getString("shortdesc"),
                                        standerdProduct.getString("rating"),
                                        (float) standerdProduct.getDouble("price"),
                                        standerdProduct.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            StanderdProductAdapter adapter = new StanderdProductAdapter(context, standerdProductsList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
