package com.taggroup.www.darzeeco.ProductsAndContents;

/**
 * Created by muhammad.sufwan on 11/28/2017.
 */


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.taggroup.www.darzeeco.R;
import com.taggroup.www.darzeeco.UsersContent.RequestHandler;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ProductDetailActivity extends AppCompatActivity {


    private String URL = "http://192.168.2.41/darzee/cart_insert.php";

    public static final String EXTRA_IMAGE = "product_image";
    public static final String EXTRA_TITLE = "product_name";
    public static final String EXTRA_SHORTDESC = "product_shortDesc";
    public static final String EXTRA_RATING = "product_rating";
    public static final String EXTRA_PRICE = "product_price";
    public static final String EXTRA_ID = "product_id";
    private String user_id = String.valueOf(SharedPrefManager.getInstance(this).getUser().getId());


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final int design_id = intent.getIntExtra(EXTRA_ID,0);
        final String productName = intent.getStringExtra(EXTRA_TITLE);
        final String productType = intent.getStringExtra(EXTRA_RATING);
        String productShortdesc = intent.getStringExtra(EXTRA_SHORTDESC);
        final float productPrice = intent.getFloatExtra(EXTRA_PRICE,0);
        final String productImage = intent.getStringExtra(EXTRA_IMAGE);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarD);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(productName);
//        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.color_black));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.color_black));

        FloatingActionButton addtocart = (FloatingActionButton)findViewById(R.id.addcartbtn);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class AddtoCart extends AsyncTask<Void, Void, String> {


                    private ProgressBar progressBar;


                    @Override
                    protected String doInBackground(Void... voids) {
                        RequestHandler requestHandler = new RequestHandler();

                        //creating request parameters
                        HashMap<String, String> params = new HashMap<>();

                        params.put("quantity", "1");
                        params.put("design_type", productType);
                        params.put("design_id", String.valueOf(design_id));
                        params.put("user_id", user_id);
                        params.put("amount", String.valueOf(productPrice));
                        params.put("image", productImage);

                        //returing the response
                        return requestHandler.sendPostRequest(URL, params);
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        //displaying the progress bar while user registers on the server
                        progressBar = (ProgressBar) findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.GONE);


                        try {

                            //converting response to json object
                            JSONObject obj = new JSONObject(s);
                            String error = obj.getString("error");


                            //if no error in response
                            if (error.equals(false)) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();



                            } else {
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();

                        }
                    }


                }

                AddtoCart ru = new AddtoCart();
                ru.execute();
            }
        });



        final TextView name,type,description,price;
        name = (TextView)findViewById(R.id.pro_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gtw.ttf");
        name.setTypeface(typeface);
        type = (TextView)findViewById(R.id.pro_des_type);
        description = (TextView)findViewById(R.id.pro_desc);
        price = (TextView)findViewById(R.id.pro_price);

        name.setText(productName);
        type.setText(productType);
        description.setText(productShortdesc);
        price.setText("Rs:"+String.valueOf(productPrice));

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this)
                .load("http://192.168.2.41/darzee/"+productImage)
                .into(imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

}