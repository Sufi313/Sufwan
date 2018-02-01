package com.taggroup.www.darzeeco.CustomerAct;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.MainActivity;
import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class BuyActivity extends AppCompatActivity {
    private Context context;
    private TextView oneTxt, twoTxt, threeTxt;
    private EditText pickaddress,contact;
    private Button home, track, pick;
    private static final String URL_Paypal = "http://192.168.2.41/darzee/paypalDelivery.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        context = this;
        home = (Button)findViewById(R.id.homeButton);
        track = (Button)findViewById(R.id.trackButton);
        pick = (Button)findViewById(R.id.buypaypal);
        pickaddress = (EditText)findViewById(R.id.buypickupAddress);
        contact = (EditText)findViewById(R.id.buycontactAddress);

        Toolbar toolbar = (Toolbar)findViewById(R.id.buyToolbar);
        setSupportActionBar(toolbar);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyActivity.this, MainActivity.class));
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyActivity.this, TrackActivity.class));

            }
        });

         oneTxt = (TextView) findViewById(R.id.textBuyone);
         twoTxt = (TextView) findViewById(R.id.textBuytwo);
         threeTxt = (TextView)findViewById(R.id.textBuythree);

        Intent intent = getIntent();
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("paymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("paymentAmount"));

        }
       catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetails(JSONObject response, String paymentAmount){
        try {
            oneTxt.setText(response.getString("id"));
            twoTxt.setText(response.getString(String.format("$",paymentAmount)));
            threeTxt.setText(response.getString("state"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashOnDelivery();
            }
        });
    }
    private void cashOnDelivery() {
        final String user_id = String.valueOf(SharedPrefManager.getInstance(context).getUser().getId());
        final String address = SharedPrefManager.getInstance(context).getUser().getCountry() + ", " + SharedPrefManager.getInstance(context).getUser().getCity();

        final String shipping_address = pickaddress.getText().toString().trim();
        final String getContact = contact.getText().toString().trim();

        if (TextUtils.isEmpty(shipping_address)) {
            pickaddress.setError("Please enter Address");
            pickaddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(getContact)) {
            contact.setError("Please enter Contact");
            contact.requestFocus();
            return;
        }


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


        class AddInvoice extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("user_id", user_id);
                params.put("address", address);
                params.put("shipping_address", shipping_address);
                params.put("contact_number", getContact);
                params.put("amount", twoTxt.getText().toString());
                params.put("invoice_date", newDateStr);
                params.put("status", threeTxt.getText().toString());
                params.put("pickup", "pending");
                params.put("deliver", "pending");

                //returing the response
                return requestHandler.sendPostRequest(URL_Paypal, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = findViewById(R.id.progressBarBuy);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {

                    JSONObject object = new JSONObject(s);

                    boolean error = object.getBoolean("error");


                    //if no error in response
                    if (error == false) {
                        Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(BuyActivity.this,TrackActivity.class));

                    } else {
                        Toast.makeText(context, object.getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        AddInvoice ai = new AddInvoice();
        ai.execute();

    }

}
