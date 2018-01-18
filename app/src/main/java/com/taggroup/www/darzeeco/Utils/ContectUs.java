package com.taggroup.www.darzeeco.Utils;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import com.taggroup.www.darzeeco.R;

public class ContectUs extends AppCompatActivity {
    private TextView emailText, privacyText, termsText, serviceText, contectText,contectNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect_us);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar_contect);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Darzee.CO");
        myToolBar.setSubtitle("ContectUs");


        contectNo = (TextView)findViewById(R.id.contectNo_text);
        emailText = (TextView) findViewById(R.id.contect_mail);
        privacyText = (TextView) findViewById(R.id.privacy_text);
        termsText = (TextView) findViewById(R.id.term_text);




        privacyText = (TextView) findViewById(R.id.privacy_text);
        privacyText.setMovementMethod(LinkMovementMethod.getInstance());

        contectText = (TextView) findViewById(R.id.contect_text);
        contectText.setMovementMethod(LinkMovementMethod.getInstance());

        serviceText = (TextView) findViewById(R.id.service_text);
        serviceText.setMovementMethod(LinkMovementMethod.getInstance());


        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText.setFocusable(true);
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, emailText.getText().toString());
                email.putExtra(Intent.EXTRA_SUBJECT, "Info");
                email.putExtra(Intent.EXTRA_TEXT, "I want info this product");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose app to send mail"));

            }
        });

        termsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(ContectUs.this);
                View myView = getLayoutInflater().inflate(R.layout.terms_dialog, null);
                myDialog.setView(myView);
                AlertDialog dialog = myDialog.create();
                dialog.show();
            }
        });


        SpannableString ss = new SpannableString(
                "  021-34322606-07"
        );
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),2,17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new URLSpan("tel:0213432260607"),2,17,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        contectNo.setText(ss);
        contectNo.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
