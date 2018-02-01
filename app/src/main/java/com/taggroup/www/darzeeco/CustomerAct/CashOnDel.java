package com.taggroup.www.darzeeco.CustomerAct;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CashOnDel extends AppCompatActivity {

    private static final String URL_CASH = "http://192.168.2.41/darzee/cashOnDelivery2.php";
    private EditText contactAddress, pickupAddress;
    public static final String EXTRA_AMOUNT = "TotalAmount";
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_del);

        context = this;
        TextView totalPrice = (TextView) findViewById(R.id.totalAmount);
        pickupAddress = (EditText) findViewById(R.id.pickupAddress);
        contactAddress = (EditText) findViewById(R.id.contactAddress);
        Button cashOnDelivery = (Button) findViewById(R.id.btnCashOnDelivery);
        String contact = SharedPrefManager.getInstance(context).getUser().getPhonenumber();
        contactAddress.setText(contact);
        Intent intent = getIntent();
        String amount = String.valueOf(intent.getFloatExtra(EXTRA_AMOUNT,-1));

        totalPrice.setText("Rs: "+amount);
        cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashOnDelivery();
            }
        });
    }

    private void cashOnDelivery() {
        Intent intent =getIntent();
        final String amount = String.valueOf(intent.getFloatExtra(EXTRA_AMOUNT,-1));
        final String user_id = String.valueOf(SharedPrefManager.getInstance(context).getUser().getId());
        final String address = SharedPrefManager.getInstance(context).getUser().getCountry() + ", " + SharedPrefManager.getInstance(context).getUser().getCity();

        final String shipping_address = pickupAddress.getText().toString().trim();
        final String getContact = contactAddress.getText().toString().trim();

        if (TextUtils.isEmpty(shipping_address)) {
            pickupAddress.setError("Please enter Address");
            pickupAddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(getContact)) {
            contactAddress.setError("Please enter Contact");
            contactAddress.requestFocus();
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
                params.put("amount", amount);
                params.put("invoice_date", newDateStr);
                params.put("pickup", "pending");
                params.put("deliver", "pending");

                //returing the response
                return requestHandler.sendPostRequest(URL_CASH, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = findViewById(R.id.progressBarCashOnDel);
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

                        startActivity(new Intent(CashOnDel.this,TrackActivity.class));

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

