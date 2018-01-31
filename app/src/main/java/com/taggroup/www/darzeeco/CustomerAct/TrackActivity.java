package com.taggroup.www.darzeeco.CustomerAct;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Adapters.TrackAdapter;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.StanderdProduct;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.StanderdProductAdapter;
import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrackActivity extends AppCompatActivity {
    private static final String URL_ORDER = "http://192.168.2.41/darzee/track_order.php";
    private Context context;
    private RecyclerView recyclerView;
    List<Track> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        Toolbar toolbar = (Toolbar)findViewById(R.id.trackToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        recyclerView = (RecyclerView)findViewById(R.id.recylcerViewOrderList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        orderList = new ArrayList<>();



    }


    class OrderList extends AsyncTask<Void, Void, String> {

        final String user_id = String.valueOf(SharedPrefManager.getInstance(context).getUser().getId());
        private ProgressBar progressBar;

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("user_id", user_id);


            //returing the response
            return requestHandler.sendPostRequest(URL_ORDER, params);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displaying the progress bar while user registers on the server
            progressBar = findViewById(R.id.progressBarTrack);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //hiding the progressbar after completion
            progressBar.setVisibility(View.GONE);

            try {
                //converting the string to json array object
                JSONArray array = new JSONArray(s);

                //traversing through all the object
                for (int i = 0; i < array.length(); i++) {

                    //getting product object from json array
                    JSONObject track = array.getJSONObject(i);

                    //adding the product to product list
                    orderList.add(new Track(
                            track.getInt("id"),
                            track.getInt("user_id"),
                            track.getDouble("amount"),
                            track.getString("address"),
                            track.getString("shipping_address"),
                            track.getString("contact_number"),
                            track.getString("status"),
                            track.getString("invoice_date"),
                            track.getString("pickup_status"),
                            track.getString("deliver_status")
                    ));
                }

                //creating adapter object and setting it to recyclerview
                TrackAdapter adapter = new TrackAdapter(context, orderList);
                recyclerView.setAdapter(adapter);


            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),  e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


}
