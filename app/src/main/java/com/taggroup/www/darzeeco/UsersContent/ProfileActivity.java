package com.taggroup.www.darzeeco.UsersContent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.taggroup.www.darzeeco.MainActivity;
import com.taggroup.www.darzeeco.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginPage.class));
            return;
        }

        TextView id = (TextView)findViewById(R.id.textp1);
        TextView fname = (TextView)findViewById(R.id.textp2);
        TextView lname = (TextView)findViewById(R.id.textp3);
        TextView email = (TextView)findViewById(R.id.textp4);
        TextView phone = (TextView)findViewById(R.id.textp5);
        TextView address = (TextView)findViewById(R.id.textp6);
        TextView gender = (TextView)findViewById(R.id.textp7);
        TextView country = (TextView)findViewById(R.id.textp8);
        TextView city = (TextView)findViewById(R.id.textp9);
        TextView dob = (TextView)findViewById(R.id.textp10);

        id.setText(String.valueOf(SharedPrefManager.getInstance(this).getUser().getId()));
        fname.setText(SharedPrefManager.getInstance(this).getUser().getFirstname());
        lname.setText(SharedPrefManager.getInstance(this).getUser().getLastname());
        email.setText(SharedPrefManager.getInstance(this).getUser().getEmail());
        phone.setText(SharedPrefManager.getInstance(this).getUser().getPhonenumber());
        address.setText(SharedPrefManager.getInstance(this).getUser().getAddress());
        gender.setText(SharedPrefManager.getInstance(this).getUser().getGender());
        country.setText(SharedPrefManager.getInstance(this).getUser().getCountry());
        city.setText(SharedPrefManager.getInstance(this).getUser().getCity());
        dob.setText(SharedPrefManager.getInstance(this).getUser().getDateofbirth());




    }
}
