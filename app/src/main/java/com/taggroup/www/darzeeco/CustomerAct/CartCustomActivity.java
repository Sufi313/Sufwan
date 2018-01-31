package com.taggroup.www.darzeeco.CustomerAct;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;

import java.util.ArrayList;

import org.json.JSONObject;

import java.math.BigDecimal;

import android.os.AsyncTask;
import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.Button;
import android.content.Intent;

import org.json.JSONException;

import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.ProgressBar;

import com.taggroup.www.darzeeco.R;

import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalPayment;

import android.support.v7.widget.LinearLayoutManager;

import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.SelectSize;


public class CartCustomActivity extends AppCompatActivity {

    private static final String URL = "http://192.168.2.41/darzee/custom_cart_retrieve.php";
    private static final String URL_CASH = "http://192.168.2.41/darzee/cashOnDelivery1.php";


    private String user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());
    //a list to store all the products
    List<CustomCart> customCartList;
    AlertDialog levelDialog;


    private Context context;
    private TextView priceTotal;
    //the recyclerview
    RecyclerView recyclerView;
    LinearLayout ifItemHave, ifItemNot;
    float totalPrice = 0;
    float pkr = (float) 110.3;

    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)

            .clientId("AejPzHTA5RM1P6pWbQFqJIoPswfJto150Xbsj_vmUyS1xEHETOYtokUzhZN-9adwFMu57qjvqKyueM7r");
            //access_token$sandbox$mmntb3srcpqbf22v$2ef13483a861601dc206e82f84506e4a
            //sufwan040491-facilitator-check@gmail.com


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_custom);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarCustomCart);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Cart");
        final Button payment = findViewById(R.id.custom_payment);
        priceTotal = findViewById(R.id.totalCustomPrice);
        context = this;

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //kdhfjksdhjkfhsd

                final CharSequence[] items = {" Paypal "," TwoCheckOut "," Cash On Delivery "};

                // Creating and Building the Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose your payment method");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        switch(item)
                        {
                            case 0:
                                PaymentClick();
                                break;
                            case 1:
                                Toast.makeText(CartCustomActivity.this, "In future", Toast.LENGTH_LONG).show();

                                break;
                            case 2:
                                cashOnDelivery();
                                break;


                        }
                        levelDialog.dismiss();
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();

            }
        });


        ifItemHave = (LinearLayout) findViewById(R.id.itemInCart);
        ifItemNot = (LinearLayout) findViewById(R.id.noItemInCart);

        TextView intent = (TextView) findViewById(R.id.customIntentBtn);

        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CartCustomActivity.this, SelectSize.class);
                startActivity(intent1);
            }
        });

        recyclerView = findViewById(R.id.recylcerViewCustomCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customCartList = new ArrayList<>();


        CartData ru = new CartData();
        ru.execute();

    }

    private void cashOnDelivery() {

                class AddInvoice extends AsyncTask<Void, Void, String> {

                    private ProgressBar progressBar;

                    @Override
                    protected String doInBackground(Void... voids) {
                        //creating request handler object
                        RequestHandler requestHandler = new RequestHandler();

                        //creating request parameters
                        HashMap<String, String> params = new HashMap<>();

                        params.put("user_id", user_id);

                        //returing the response
                        return requestHandler.sendPostRequest(URL_CASH, params);
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        //displaying the progress bar while user registers on the server
                        progressBar = findViewById(R.id.progressBarCustomCart);
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

                                Intent intent = new Intent(context,CashOnDel.class);
                                intent.putExtra("TotalAmount",totalPrice);
                                context.startActivity(intent);

                            } else {
                                Toast.makeText(context, object.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } AddInvoice ai = new AddInvoice();
                ai.execute();

            }



    class CartData extends AsyncTask<Void, Void, String> {

        private ProgressBar progressBar;

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("user_id", user_id);


            //returing the response
            return requestHandler.sendPostRequest(URL, params);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displaying the progress bar while user registers on the server
            progressBar = findViewById(R.id.progressBarCustomCart);
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
                    JSONObject customCart = array.getJSONObject(i);

                    //adding the product to product list
                    customCartList.add(new CustomCart(

                            customCart.getInt("id"),
                            customCart.getInt("size_id"),
                            customCart.getInt("user_id"),
                            customCart.getString("design_id"),
                            customCart.getString("design_type"),
                            (float) customCart.getDouble("order_amount")

                    ));


                }


                //creating adapter object and setting it to recyclerview
                CustomCartAdapter adapter = new CustomCartAdapter(CartCustomActivity.this, customCartList);
                recyclerView.setAdapter(adapter);
                ifItemHave.setVisibility(View.VISIBLE);

                if (customCartList == null) {
                    ifItemNot.setVisibility(View.VISIBLE);
                }

                for (int i = 0; i < customCartList.size(); i++) {
                    totalPrice += customCartList.get(i).getOrder_amount();
                }


                priceTotal.setText(String.valueOf("Rs:" + totalPrice));
                pkr = totalPrice / pkr;


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void PaymentClick() {

        // PAYMENT_INTENT_SALE will cause the payment to complete immediately.
        // Change PAYMENT_INTENT_SALE to
        //   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
        //   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
        //     later via calls from your server.

        PayPalPayment payment = new PayPalPayment(new BigDecimal(pkr), "US", "Being payment for it" +
                "ems ordered",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.
                    String paymentDetails = confirm.toJSONObject().toString();
                    startActivity(new Intent(this, BuyActivity.class)
                            .putExtra("paymentDetails", paymentDetails)
                            .putExtra("paymentAmount", pkr));
                    Toast.makeText(this, "Thanks for your purchasing", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
            Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            Toast.makeText(this, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.", Toast.LENGTH_SHORT).show();
        }
    }


}
