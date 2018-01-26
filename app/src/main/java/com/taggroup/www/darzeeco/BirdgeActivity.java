package com.taggroup.www.darzeeco;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.CustomerAct.CartActivity;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.StanderdDesign;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.LetSelectSizePrefMngr;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class BirdgeActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.2.41/darzee/AddToCart.php";

    public static final String EXTRA_IMAGE = "design_image";
    public static final String EXTRA_TYPE = "design_type";
    public static final String EXTRA_PRICE = "design_price";
    public static final String EXTRA_ID = "design_id";
    private String sizeName = LetSelectSizePrefMngr.getInstance(this).getSizeName();
    private String size_id = String.valueOf(LetSelectSizePrefMngr.getInstance(this).getSizeId());
    private String user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birdge);

        Intent intent = getIntent();

        final String designType = intent.getStringExtra(EXTRA_TYPE);
        final String designImage = intent.getStringExtra(EXTRA_IMAGE);
        final String designPrice = intent.getStringExtra(EXTRA_PRICE);
        final int designId = intent.getIntExtra(EXTRA_ID, 0);

        context = this;

        TextView text = findViewById(R.id.bridgeText);
        TextView text1 = findViewById(R.id.bridgeText1);
        TextView text2 = findViewById(R.id.bridgeText2);
        TextView text3 = findViewById(R.id.bridgeText3);
        TextView text4 = findViewById(R.id.bridgeText4);
        TextView text5 = findViewById(R.id.bridgeText5);
        TextView text6 = findViewById(R.id.bridgeText6);

        text.setText("NAME " + designType);
        text1.setText("IMAGE " + designImage);
        text2.setText("SIZE NAME " + sizeName);
        text3.setText("PRICE " + designPrice);
        text4.setText("DESIGN ID " + String.valueOf(designId));
        text5.setText("SIZE ID " + size_id);
        text6.setText(user_id);


        class AddToCart extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("size_id", size_id);
                params.put("design_id", String.valueOf(designId));
                params.put("design_type", designType);
                params.put("design_amount", designPrice);
                params.put("design_image", designImage);


                //returing the response
                return requestHandler.sendPostRequest(URL, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = findViewById(R.id.progressBarBridge);
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

                        Intent intent = new Intent(BirdgeActivity.this, CartActivity.class);
                        LetSelectSizePrefMngr.getInstance(context).ClearSize();
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred " + obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        AddToCart addToCart = new AddToCart();
        addToCart.execute();

    }
}
