package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.taggroup.www.darzeeco.FRAGS.FragmentCustomBack;
import com.taggroup.www.darzeeco.FRAGS.FragmentCustomFront;
import com.taggroup.www.darzeeco.FRAGS.FragmentDaaman;
import com.taggroup.www.darzeeco.FRAGS.FragmentDupatta;
import com.taggroup.www.darzeeco.FRAGS.FragmentShalwar;
import com.taggroup.www.darzeeco.R;


public class CustomizedDesign extends AppCompatActivity {

    public final static String TAG = "CustomizedDesign";
    private Fragment fragment = null;
    private TextView nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_design);

        Toolbar toolbar = (Toolbar) findViewById(R.id.customizedDesignToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nextBtn = (TextView)findViewById(R.id.nextProccess);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Log.d(TAG, "Customized Activity start");

        fragment = new FragmentCustomFront();
        CheckFrag();

        fragment = new FragmentCustomBack();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.backCustomSelect,fragment);
        fragment = new FragmentDaaman();
        ft.replace(R.id.daamanCustomSelect,fragment);
        fragment = new FragmentShalwar();
        ft.replace(R.id.shalwarCustomSelect,fragment);
        fragment = new FragmentDupatta();
        ft.replace(R.id.dupattaCustomSelect,fragment);
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

}
