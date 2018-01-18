package com.taggroup.www.darzeeco.CustomizeAndStanderd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        Toolbar myToolBar =
                (Toolbar) findViewById(R.id.selectCategoryDesignToolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final int design_id = intent.getIntExtra(EXTRA_ID,0);
        final String sizeName = intent.getStringExtra(EXTRA_SIZE_NAME);

        // Binding the text Views for select next proccess
        standerdDesign = (TextView)findViewById(R.id.standerdCtg);
        customizedDesign = (TextView)findViewById(R.id.customeCtg);
        perMaterial = (TextView)findViewById(R.id.materialCtg);
        perMeasurment = (TextView)findViewById(R.id.measureCtg);
        bridal = (TextView)findViewById(R.id.bridalCtg);

        standerdDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectDesignCategory.this,StanderdDesign.class);
                startActivity(myIntent);

//                Intent i = new Intent(context,StanderdDesign.class);
//                i.putExtra("size_id",design_id);
//                i.putExtra("user_size_name",sizeName);
//                context.startActivity(i);

            }
        });

        customizedDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(SelectDesignCategory.this,CustomizedDesign.class);
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
               Intent intent2 = new Intent(SelectDesignCategory.this,Bridal.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
