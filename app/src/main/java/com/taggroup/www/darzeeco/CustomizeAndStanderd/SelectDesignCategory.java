package com.taggroup.www.darzeeco.CustomizeAndStanderd;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.Bridal;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.CustomizedDesign;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories.StanderdDesign;
import com.taggroup.www.darzeeco.R;

public class SelectDesignCategory extends AppCompatActivity {

    private TextView standerdDesign, customizedDesign, perMaterial, perMeasurment, bridal;

    private Context context;

    public static final String EXTRA_ID = "size_id";
    public static final String EXTRA_SIZE_NAME = "user_size_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_design_category);

        context = this;

        Toolbar myToolBar =
                findViewById(R.id.selectCategoryDesignToolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final int size_id = intent.getIntExtra(EXTRA_ID, 0);
        final String sizeName = intent.getStringExtra(EXTRA_SIZE_NAME);

        if (!LetSelectSizePrefMngr.getInstance(context).isSizeIn()){
            startActivity(new Intent(SelectDesignCategory.this, SelectSize.class));
        }

        // Binding the text Views for select next proccess
        standerdDesign = findViewById(R.id.standerdCtg);
        customizedDesign = findViewById(R.id.customeCtg);
        perMaterial = findViewById(R.id.materialCtg);
        perMeasurment = findViewById(R.id.measureCtg);
        bridal = findViewById(R.id.bridalCtg);

        ImageView testSizeCondi = findViewById(R.id.testSizeCondi);
        testSizeCondi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetSelectSizePrefMngr.getInstance(context).ClearSize();
            }
        });

        TextView checkText = findViewById(R.id.checkText);
        checkText.setText("USER SIZE ID: " + String.valueOf(size_id));

        standerdDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(context, StanderdDesign.class);
                i.putExtra("size_id", size_id);
                i.putExtra("user_size_name", sizeName);
                context.startActivity(i);

            }
        });

        customizedDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(SelectDesignCategory.this, CustomizedDesign.class);
                startActivity(myIntent);

            }
        });

        perMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        perMeasurment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bridal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SelectDesignCategory.this, Bridal.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
