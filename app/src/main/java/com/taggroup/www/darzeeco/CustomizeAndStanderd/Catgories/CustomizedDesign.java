package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

import java.util.Date;

import android.util.Log;
import android.view.View;

import java.util.HashMap;

import android.os.Bundle;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import android.content.Intent;
import android.widget.TextView;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.taggroup.www.darzeeco.CustomerAct.CartCustomActivity;
import com.taggroup.www.darzeeco.R;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import com.taggroup.www.darzeeco.FRAGS.FragmentDaaman;
import com.taggroup.www.darzeeco.FRAGS.FragmentShalwar;
import com.taggroup.www.darzeeco.FRAGS.FragmentDupatta;
import com.taggroup.www.darzeeco.CustomerAct.CartActivity;
import com.taggroup.www.darzeeco.FRAGS.FragmentCustomBack;
import com.taggroup.www.darzeeco.FRAGS.FragmentCustomFront;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.SelectSize;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.LetSelectSizePrefMngr;


public class CustomizedDesign extends AppCompatActivity {

    private static final String URL = "http://192.168.2.41/darzee/AddToCartCustom.php";
    public final static String TAG = "CustomizedDesign";
    private Fragment fragment = null;
    private TextView nextBtn, allBtn, oneBtn, twoBtn;
    private Context mContext;
    TextView testing;

    private boolean allBool, oneBool, twoBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_design);

        Toolbar toolbar = findViewById(R.id.customizedDesignToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;

        allBtn = findViewById(R.id.compeletecustombtn);
        twoBtn = findViewById(R.id.twocustombtn);
        oneBtn = findViewById(R.id.shirtcustombtn);

        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBool = true;
            }
        });


        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoBool = true;
                oneBool = false;
                allBool = false;

            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneBool = true;
                allBool = false;
                twoBool = false;
            }
        });

        testing = findViewById(R.id.testingOnfloatPlus);

        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetSelectSizePrefMngr.getInstance(mContext).ClearSelectFrontNeck();
                LetSelectSizePrefMngr.getInstance(mContext).ClearSelectBackNeck();
                LetSelectSizePrefMngr.getInstance(mContext).ClearSelectShalwar();
                LetSelectSizePrefMngr.getInstance(mContext).ClearSelectDaaman();
                LetSelectSizePrefMngr.getInstance(mContext).ClearSelectDupatta();
            }
        });

        nextBtn = findViewById(R.id.nextProccess);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddedCustom();

            }
        });

        Log.d(TAG, "Customized Activity start");

        fragment = new FragmentCustomFront();
        CheckFrag();

        fragment = new FragmentCustomBack();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.backCustomSelect, fragment);
        fragment = new FragmentDaaman();
        ft.replace(R.id.daamanCustomSelect, fragment);
        fragment = new FragmentShalwar();
        ft.replace(R.id.shalwarCustomSelect, fragment);
        fragment = new FragmentDupatta();
        ft.replace(R.id.dupattaCustomSelect, fragment);
        ft.commit();

    }

    public void CheckFrag() {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frontCustomSelect, fragment);
            ft.commit();
        }
    }

    public void AddedCustom() {

        if (allBool == true) {
            if (!LetSelectSizePrefMngr.getInstance(mContext).isFrontNeckIn()) {
                Toast.makeText(mContext, "Please select front neck", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isBackNeckIn()) {
                Toast.makeText(mContext, "Please select back neck", Toast.LENGTH_SHORT).show();
                return;

            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isDaamanIn()) {
                Toast.makeText(mContext, "Please select daaman", Toast.LENGTH_SHORT).show();
                return;

            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isShalwarIn()) {
                Toast.makeText(mContext, "Please select paints", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isDupattaIn()) {
                Toast.makeText(mContext, "Please select dupatta", Toast.LENGTH_SHORT).show();
                return;

            }
        }


        if (twoBool == true) {
            if (!LetSelectSizePrefMngr.getInstance(mContext).isFrontNeckIn()) {
                Toast.makeText(mContext, "Please select front neck", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isBackNeckIn()) {
                Toast.makeText(mContext, "Please select back neck", Toast.LENGTH_SHORT).show();
                return;

            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isDaamanIn()) {
                Toast.makeText(mContext, "Please select daaman", Toast.LENGTH_SHORT).show();
                return;

            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isShalwarIn()) {
                Toast.makeText(mContext, "Please select paints", Toast.LENGTH_SHORT).show();
                return;

            }
        }

        if (oneBool == true) {
            if (!LetSelectSizePrefMngr.getInstance(mContext).isFrontNeckIn()) {
                Toast.makeText(mContext, "Please select front neck", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isBackNeckIn()) {
                Toast.makeText(mContext, "Please select back neck", Toast.LENGTH_SHORT).show();
                return;

            }
            if (!LetSelectSizePrefMngr.getInstance(mContext).isDaamanIn()) {
                Toast.makeText(mContext, "Please select daaman", Toast.LENGTH_SHORT).show();
                return;

            }
        }



        final String user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());
        final String size_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getSizeId());
        final String front_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getFrontNeckId());
        final String back_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getBackNeckId());
        final String shalwar_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getShalwarId());
        final String daaman_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getDaamanId());
        final String dupatta_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getDupattaId());

        final float front_price = LetSelectSizePrefMngr.getInstance(this).getFrontNeckPrice();
        final float back_price = LetSelectSizePrefMngr.getInstance(this).getBackNeckPrice();
        final float shalwar_price = LetSelectSizePrefMngr.getInstance(this).getShalwarPrice();
        final float daaman_price = LetSelectSizePrefMngr.getInstance(this).getDaamanPrice();
        final float dupatta_price = LetSelectSizePrefMngr.getInstance(this).getDupattaPrice();

        final String design_id = front_id + "-" + back_id + "-" + shalwar_id + "-" + daaman_id + "-" + dupatta_id;

        Toast.makeText(mContext, design_id, Toast.LENGTH_LONG).show();

        final float order_amount = front_price + back_price + shalwar_price + daaman_price + dupatta_price;

        String dateStr = "04/05/2010";

        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("yyyy-MM-dd");

        final String newDateStr = postFormater.format(dateObj);

        Toast.makeText(mContext, newDateStr, Toast.LENGTH_SHORT).show();

        class CustomAddToCart extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("user_id", user_id);
                params.put("size_id", size_id);
                params.put("design_id", design_id);
                params.put("design_type", "custom");
                params.put("order_date", newDateStr);
                params.put("order_amount", String.valueOf(order_amount));


                //returing the response
                return requestHandler.sendPostRequest(URL, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //displaying the progress bar while user add data in data base on the server
                progressBar = findViewById(R.id.progressBarCustom);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {

                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    boolean error = obj.getBoolean("error");


                    //if no error in response
                    if (error == false) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        LetSelectSizePrefMngr.getInstance(mContext).ClearSelectFrontNeck();
                        LetSelectSizePrefMngr.getInstance(mContext).ClearSelectBackNeck();
                        LetSelectSizePrefMngr.getInstance(mContext).ClearSelectShalwar();
                        LetSelectSizePrefMngr.getInstance(mContext).ClearSelectDaaman();
                        LetSelectSizePrefMngr.getInstance(mContext).ClearSelectDupatta();

                        startActivity(new Intent(getApplicationContext(), CartCustomActivity.class));
                    } else {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }

        CustomAddToCart catc = new CustomAddToCart();
        catc.execute();
    }
}
//google drive api key   "AIzaSyC6-uYNDxjNtytPjzSVFTXNawUh0ZLORzc"