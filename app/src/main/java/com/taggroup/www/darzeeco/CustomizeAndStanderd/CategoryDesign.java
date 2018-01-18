package com.taggroup.www.darzeeco.CustomizeAndStanderd;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

// Created By Sufwan Ansari

public class CategoryDesign extends AppCompatActivity {

    private String url = "http://192.168.2.41/darzee/get_size.php";

    private RadioGroup radioGroup;
    private int checked;
    private ImageView testActivity;

    private TextView showChart, submit;
    private EditText fullName, sShoulder, sChest, sWaist, sArmHolle, sSleeveLength, sSleeve, sDaaman, sLength,
            tWaist, tHip, tThigh, tKnee, tLength, tInseamLength, tOpening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_design);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.categoryDesignToolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        if (SharedPrefManagerSize.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, SelectSize.class));
//            return;
//        }


        // Bined all text fields

        fullName = (EditText) findViewById(R.id.sizeFullName);
        sShoulder = (EditText) findViewById(R.id.shirtShoulder);
        sChest = (EditText) findViewById(R.id.shirtChest);
        sWaist = (EditText) findViewById(R.id.shirtWaist);
        sArmHolle = (EditText) findViewById(R.id.shirtArmHole);
        sSleeveLength = (EditText) findViewById(R.id.shirtSleevelLength);
        sSleeve = (EditText) findViewById(R.id.shirtSleeve);
        sDaaman = (EditText) findViewById(R.id.shirtDaaman);
        sLength = (EditText) findViewById(R.id.shirtLength);
        tWaist = (EditText) findViewById(R.id.trouserWaist);
        tHip = (EditText) findViewById(R.id.trouserHip);
        tThigh = (EditText) findViewById(R.id.trouserThigh);
        tKnee = (EditText) findViewById(R.id.trouserKnee);
        tLength = (EditText) findViewById(R.id.trouserLength);
        tOpening = (EditText) findViewById(R.id.trouserOpening);
        tInseamLength = (EditText) findViewById(R.id.trouserInseamLength);

        // Bined text Button

        showChart = (TextView) findViewById(R.id.showChart);
        submit = (TextView) findViewById(R.id.submitSize);


        // testing gor check next Activity
        testActivity = (ImageView)findViewById(R.id.next_skip_test);


        testActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryDesign.this, SelectSize.class));
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveSize();


            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.standerdSizeGroup);

        showChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(CategoryDesign.this);
                View myView = getLayoutInflater().inflate(R.layout.show_chart, null);
                myDialog.setView(myView);
                AlertDialog dialog = myDialog.create();
                dialog.show();
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked = radioGroup.indexOfChild(findViewById(checkedId));

                switch (checked) {

                    case 0:
                        Toast.makeText(getApplicationContext(), "EXTRA SMALL SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("1");

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "SMALL  SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("2");
                        break;

                    case 2:
                        Toast.makeText(getApplicationContext(), "MEDIUM SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("3");
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "LARGE SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("4");
                        break;

                    case 4:
                        Toast.makeText(getApplicationContext(), "EXTRA LARGE SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("5");
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "X EXTRA LARGE SIZE", Toast.LENGTH_SHORT).show();
                        userGetSize("6");
                        break;

                }
            }
        });

    }

    private void saveSize() {

        final String fullNameP = fullName.getText().toString().trim();
        final String shoulder_s = sShoulder.getText().toString().trim();
        final String chest_s = sChest.getText().toString().trim();
        final String waist_s = sWaist.getText().toString().trim();
        final String armhole_s = sArmHolle.getText().toString().trim();
        final String sleevelength_s = sSleeveLength.getText().toString().trim();
        final String sleeve_s = sSleeve.getText().toString().trim();
        final String daaman_s = sDaaman.getText().toString().trim();
        final String length_s = sLength.getText().toString().trim();
        final String twaist_s = tWaist.getText().toString().trim();
        final String thip_s = tHip.getText().toString().trim();
        final String tthigh_s = tThigh.getText().toString().trim();
        final String tknee_s = tKnee.getText().toString().trim();
        final String tlength_s = tLength.getText().toString().trim();
        final String topening_s = tOpening.getText().toString().trim();
        final String tinseamlength_s = tInseamLength.getText().toString().trim();
        final String user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());


        //first we will do the validations

        if (TextUtils.isEmpty(fullNameP)) {
            fullName.setError("Please enter Full Name");
            fullName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(shoulder_s)) {
            sShoulder.setError("Please enter Shoulder Size");
            sShoulder.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(chest_s)) {
            sChest.setError("Please enter Chest Size");
            sChest.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(waist_s)) {
            sWaist.setError("Enter enter Shirt Waist Size");
            sWaist.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(armhole_s)) {
            sArmHolle.setError("Please enter Arm Hole Size");
            sArmHolle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sleevelength_s)) {
            sSleeveLength.setError("Plesae enter Sleeve Length Size");
            sSleeveLength.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sleeve_s)) {
            sSleeve.setError("Please enter Sleeve Size");
            sSleeve.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(daaman_s)) {
            sDaaman.setError("Please enter Daaman Size");
            sDaaman.requestFocus();
            return;

        }
        if (TextUtils.isEmpty(length_s)) {
            sLength.setError("Please enter Shirt Length Size");
            sLength.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(twaist_s)) {
            tWaist.setError("Please enter Trouser Waist Size");
            tWaist.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(thip_s)) {
            tHip.setError("Plaese enter Hip Size");
            tHip.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tthigh_s)) {
            tThigh.setError("Please enter Thigh Size");
            tThigh.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tknee_s)) {
            tKnee.setError("Please enter Knee Size");
            tKnee.requestFocus();
            return;

        }
        if (TextUtils.isEmpty(tlength_s)) {
            tLength.setError("Please enter Trouser Length");
            tLength.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(topening_s)) {
            tOpening.setError("Please enter Opening Legth Size");
            tOpening.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tinseamlength_s)) {
            tInseamLength.setError("Please enter Inseam Length Size");
            tInseamLength.requestFocus();
            return;
        }


        class saveSizeUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> size = new HashMap<>();
                size.put("user_size_name", fullNameP);
                size.put("shoulder", shoulder_s);
                size.put("chest", chest_s);
                size.put("waist", waist_s);
                size.put("arm_hole", armhole_s);
                size.put("sleeve_length", sleevelength_s);
                size.put("sleeve", sleeve_s);
                size.put("daaman", daaman_s);
                size.put("shirt_length", length_s);
                size.put("trouser_waist", twaist_s);
                size.put("hip", thip_s);
                size.put("thigh", tthigh_s);
                size.put("knee", tknee_s);
                size.put("trouser_length", tlength_s);
                size.put("opening", topening_s);
                size.put("inseam_length", tinseamlength_s);
                size.put("user_id", user_id);

                //returing the response
                return requestHandler.sendPostRequest("http://192.168.2.41/darzee/save_size.php", size);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBarCD);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.getBoolean("error")) {
                        Toast.makeText(CategoryDesign.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //converting response to json object


                        JSONObject userSizeJson = obj.getJSONObject("savesize");

                        //creating a new size object
                        Sizes sizes;
                        sizes = new Sizes(
                                userSizeJson.getInt("id"),
                                userSizeJson.getInt("user_id"),
                                userSizeJson.getString("user_size_name"),
                                (float) userSizeJson.getDouble("shoulder"),
                                (float) userSizeJson.getDouble("chest"),
                                (float) userSizeJson.getDouble("waist"),
                                (float) userSizeJson.getDouble("arm_hole"),
                                (float) userSizeJson.getDouble("sleeve_length"),
                                (float) userSizeJson.getDouble("sleeve"),
                                (float) userSizeJson.getDouble("daaman"),
                                (float) userSizeJson.getDouble("shirt_length"),
                                (float) userSizeJson.getDouble("trouser_waist"),
                                (float) userSizeJson.getDouble("hip"),
                                (float) userSizeJson.getDouble("thigh"),
                                (float) userSizeJson.getDouble("knee"),
                                (float) userSizeJson.getDouble("trouser_length"),
                                (float) userSizeJson.getDouble("opening"),
                                (float) userSizeJson.getDouble("inseam_length")

                        );

                        //storing the user in shared preferences
                        SharedPrefManagerSize.getInstance(getApplicationContext()).userLoginSize(sizes);

                        //starting the profile activity

                        Intent intent = new Intent(CategoryDesign.this,SelectSize.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(CategoryDesign.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }
        saveSizeUser gs = new saveSizeUser();
        gs.execute();

    }


    private void userGetSize(final String s_id) {


        class UserGetSize extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBarCD);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {

                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
//


                    JSONObject userSizeJson = obj.getJSONObject("getsize");

                    //creating a new size object
                    Sizes sizes;
                    sizes = new Sizes(
                            userSizeJson.getInt("id"),
                            userSizeJson.getInt("user_id"),
                            userSizeJson.getString("sizename"),
                            (float) userSizeJson.getDouble("sshoulder"),
                            (float) userSizeJson.getDouble("schest"),
                            (float) userSizeJson.getDouble("swaist"),
                            (float) userSizeJson.getDouble("sarmhole"),
                            (float) userSizeJson.getDouble("ssleevelength"),
                            (float) userSizeJson.getDouble("ssleeve"),
                            (float) userSizeJson.getDouble("sdaaman"),
                            (float) userSizeJson.getDouble("slength"),
                            (float) userSizeJson.getDouble("twaist"),
                            (float) userSizeJson.getDouble("thip"),
                            (float) userSizeJson.getDouble("tthigh"),
                            (float) userSizeJson.getDouble("tknee"),
                            (float) userSizeJson.getDouble("tlength"),
                            (float) userSizeJson.getDouble("topening"),
                            (float) userSizeJson.getDouble("tinseamlength")

                    );

                    sShoulder.setText(String.valueOf(sizes.getsShoulder()));
                    sChest.setText(String.valueOf(sizes.getsChest()));
                    sWaist.setText(String.valueOf(sizes.getsWaist()));
                    sArmHolle.setText(String.valueOf(sizes.getsArmHole()));
                    sSleeveLength.setText(String.valueOf(sizes.getsSleeveLength()));
                    sSleeve.setText(String.valueOf(sizes.getsSleeve()));
                    sDaaman.setText(String.valueOf(sizes.getsDaaman()));
                    sLength.setText(String.valueOf(sizes.getsLength()));
                    tWaist.setText(String.valueOf(sizes.gettWaist()));
                    tHip.setText(String.valueOf(sizes.gettHip()));
                    tThigh.setText(String.valueOf(sizes.gettThigh()));
                    tKnee.setText(String.valueOf(sizes.gettKnee()));
                    tLength.setText(String.valueOf(sizes.gettLength()));
                    tOpening.setText(String.valueOf(sizes.gettOpening()));
                    tInseamLength.setText(String.valueOf(sizes.gettInseamLength()));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("ponka", String.valueOf(e));
                    Toast.makeText(getApplicationContext(), "Ponka " + e, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("s_id", s_id);
                //returing the response
                return requestHandler.sendPostRequest(url, params);
            }
        }

        UserGetSize gs = new UserGetSize();
        gs.execute();

    }


}
