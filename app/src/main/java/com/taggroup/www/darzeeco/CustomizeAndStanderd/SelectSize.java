package com.taggroup.www.darzeeco.CustomizeAndStanderd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectSize extends AppCompatActivity {

    private static final String URL_SIZE_LIST = "http://192.168.2.41/darzee/get_size_list.php";

    //a list to store all the Sizes
    List<Sizes> sizeList;

    //the recyclerview
    RecyclerView recyclerView;

    private String user_id;

    private ImageView image_male, image_female;
    private TextView newSizeAdd, profilename;
    private String checkgender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size);

        Toolbar toolBar = (Toolbar)findViewById(R.id.selectSizeToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image_male = (ImageView) findViewById(R.id.maleImageProfile);
        image_female = (ImageView) findViewById(R.id.femaleImageProfile);


        newSizeAdd = (TextView) findViewById(R.id.new_size_add);
        profilename = (TextView) findViewById(R.id.profile_name);

        final String firstN = SharedPrefManager.getInstance(this).getUser().getFirstname();
        final String secondN = SharedPrefManager.getInstance(this).getUser().getLastname();

        final String name_person = firstN + " " + secondN;
        profilename.setText(name_person);

        user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());

        checkgender = SharedPrefManager.getInstance(this).getUser().getGender();
        if (checkgender.equals("Male")) {
            image_male.setVisibility(View.VISIBLE);

        } else {
            image_female.setVisibility(View.VISIBLE);
        }
        newSizeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefManagerSize.getInstance(getApplicationContext()).ClearSize();
                finish();
                startActivity(new Intent(getApplicationContext(), CategoryDesign.class));

            }
        });


        recyclerView = findViewById(R.id.sizelist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false));

        sizeList = new ArrayList<>();

        SizeList sl = new SizeList();
        sl.execute();


    }

    class SizeList extends AsyncTask<Void, Void, String> {

        private ProgressBar progressBar;

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("user_id", user_id);


            //returing the response
            return requestHandler.sendPostRequest(URL_SIZE_LIST, params);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displaying the progress bar while user registers on the server
            progressBar = (ProgressBar) findViewById(R.id.progressBarSL);
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
                    JSONObject size = array.getJSONObject(i);

                    //adding the product to product list
                    sizeList.add(new Sizes(
                            size.getInt("id"),
                            size.getInt("user_id"),
                            size.getString("user_size_name"),
                            (float) size.getDouble("shoulder"),
                            (float) size.getDouble("chest"),
                            (float) size.getDouble("waist"),
                            (float) size.getDouble("arm_hole"),
                            (float) size.getDouble("sleeve_length"),
                            (float) size.getDouble("sleeve"),
                            (float) size.getDouble("daaman"),
                            (float) size.getDouble("shirt_length"),
                            (float) size.getDouble("trouser_waist"),
                            (float) size.getDouble("hip"),
                            (float) size.getDouble("thigh"),
                            (float) size.getDouble("knee"),
                            (float) size.getDouble("trouser_length"),
                            (float) size.getDouble("opening"),
                            (float) size.getDouble("inseam_length")
                    ));
                }

                //creating adapter object and setting it to recyclerview
                SizeAdapter adapter = new SizeAdapter(getApplicationContext(), sizeList);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
            }
        }
    }

}
