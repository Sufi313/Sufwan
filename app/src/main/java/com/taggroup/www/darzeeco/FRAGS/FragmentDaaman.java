package com.taggroup.www.darzeeco.FRAGS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Adapters.CustomProductAdapterDaaman;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.CustomProduct;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Adapters.CustomProductAdapterFront;
import com.taggroup.www.darzeeco.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentDaaman extends Fragment {
    private static final String URL_PRODUCTS = "http://192.168.2.41/darzee/daaman.php";

    //a list to store all the products
    List<CustomProduct> customProductsList;

    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daaman,null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.daamanCustomDesign);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false));

        customProductsList = new ArrayList<>();

        loadProducts();

    }

    private void loadProducts() {


        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
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
                            CustomProductAdapterDaaman adapter = new CustomProductAdapterDaaman(getContext(), customProductsList);
                            recyclerView.setAdapter(adapter);


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
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }



}
