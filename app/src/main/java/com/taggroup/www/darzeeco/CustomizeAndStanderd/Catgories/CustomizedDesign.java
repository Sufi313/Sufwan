package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

public class CustomizedDesign extends AppCompatActivity {

    public final static String TAG = "CustomizedDesign";

    private RecyclerView frontNeck, backNeck, daaman, shalwar, dopatta;

    private Context context;
    List<CustomProduct> customProductsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_design);

        Toolbar toolbar = (Toolbar)findViewById(R.id.customizedDesignToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frontNeck=(RecyclerView)findViewById(R.id.frontCustomSelect);
        backNeck=(RecyclerView)findViewById(R.id.backCustomSelect);
        daaman=(RecyclerView)findViewById(R.id.daamanCustomSelect);
        shalwar=(RecyclerView)findViewById(R.id.shalwarCustomSelect);
        dopatta=(RecyclerView)findViewById(R.id.dopattaCustomSelect);


        frontNeck.setHasFixedSize(true);
        frontNeck.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        customProductsList = new ArrayList<>();

        Log.d(TAG,"Customized Activity start");

        loadProducts("http://192.168.2.41/darzee/front.php");

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
                                JSONObject customProduct = array.getJSONObject(i);

                                //adding the product to product list
                                customProductsList.add(new CustomProduct(
                                        customProduct.getInt("id"),
                                        customProduct.getString("name"),
                                        (float) customProduct.getDouble("price"),
                                        customProduct.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            CustomProductAdapter adapter = new CustomProductAdapter(context, customProductsList);
                            frontNeck.setAdapter(adapter);



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

}
