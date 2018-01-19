package com.taggroup.www.darzeeco.UsersContent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.taggroup.www.darzeeco.R;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private AdView mAdView;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Button choose, upload;
    private ImageView imageView;

    private StorageReference storageReference;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar)findViewById(R.id.profileActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Profile");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        choose = (Button)findViewById(R.id.btnChoose);
        upload = (Button)findViewById(R.id.btnUpload);
        imageView = (ImageView)findViewById(R.id.profileImage);

        storageReference = FirebaseStorage.getInstance().getReference();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginPage.class));
            return;
        }


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });


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



    private void uploadFile(){

        if (filePath != null){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
        StorageReference riversRef = storageReference.child("images/profile.jpg");

        riversRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "File Uploaded.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage(((int) progress) + "% Uploaded...");

            }
        })
        ;
        }else {
            Toast.makeText(this, "Please choose an image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                filePath = data.getData();

            Glide.with(this).load(filePath).into(imageView);

           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST);
    }
}
