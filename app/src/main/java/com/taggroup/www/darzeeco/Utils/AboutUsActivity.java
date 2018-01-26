package com.taggroup.www.darzeeco.Utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.taggroup.www.darzeeco.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar myToolBar= findViewById(R.id.toolbar_about);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Darzee.CO");
        myToolBar.setSubtitle("AboutUs");
//        myToolBar.setLogo(R.drawable.ic_left_back);

    }




}
