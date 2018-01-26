package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taggroup.www.darzeeco.R;

public class UploadDesignCategory extends AppCompatActivity {

    public static final String UPLOAD_URL = "http://192.168.2.41/darzee/upload.php";
    public static final String IMAGES_URL = "http://192.168.94.1/AndroidImageUpload/getImages.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_design_category);

    }
}
