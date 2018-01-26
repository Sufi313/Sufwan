package com.taggroup.www.darzeeco.Starter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taggroup.www.darzeeco.R;

public class Loader extends AppCompatActivity {
    ProgressBar progressbar;
    TextView updateProgressBar,downup;
    private int progressCount = 0;
    private Handler progressHandler = new Handler();
    LinearLayout l1,l2;
    Animation uptodown,downtoup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);



        downup= findViewById(R.id.text_downup);
        progressbar= findViewById(R.id.progress_view);
        updateProgressBar= findViewById(R.id.progressinfo);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/header_font.ttf");
        downup.setTypeface(typeface);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressCount < 100) {
                    progressCount++;

                    android.os.SystemClock.sleep(50);
                    progressHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressbar.setProgress(progressCount);
                            updateProgressBar.setText("Load " + progressCount + "%");
                        }
                    });
                    if (progressCount == 100) {

                        Intent intent = new Intent(Loader.this, SlideTour.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }

        }).start();










    }
}
