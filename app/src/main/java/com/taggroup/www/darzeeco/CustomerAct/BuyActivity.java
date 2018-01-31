package com.taggroup.www.darzeeco.CustomerAct;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taggroup.www.darzeeco.MainActivity;
import com.taggroup.www.darzeeco.R;

import org.json.JSONException;
import org.json.JSONObject;

public class BuyActivity extends AppCompatActivity {

    private TextView oneTxt, twoTxt, threeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Button home = (Button)findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyActivity.this, MainActivity.class));
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
            twoTxt.setText(response.getString(String.format("$%s",paymentAmount)));
            threeTxt.setText(response.getString("state"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
