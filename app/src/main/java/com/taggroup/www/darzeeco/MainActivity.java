package com.taggroup.www.darzeeco;

//        selected imports

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.taggroup.www.darzeeco.CustomerAct.BuyActivity;
import com.taggroup.www.darzeeco.CustomerAct.CartActivity;
import com.taggroup.www.darzeeco.CustomerAct.CartCustomActivity;
import com.taggroup.www.darzeeco.CustomerAct.CustomCart;
import com.taggroup.www.darzeeco.CustomerAct.FavoriteActivity;
import com.taggroup.www.darzeeco.CustomerAct.TrackActivity;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.CategoryDesign;
import com.taggroup.www.darzeeco.CustomizeAndStanderd.LetSelectSizePrefMngr;
import com.taggroup.www.darzeeco.FRAGS.FragmentAll;
import com.taggroup.www.darzeeco.FRAGS.FragmentComplete;
import com.taggroup.www.darzeeco.FRAGS.FragmentShirts;
import com.taggroup.www.darzeeco.FRAGS.FragmentTrousers;
import com.taggroup.www.darzeeco.UsersContent.LoginPage;
import com.taggroup.www.darzeeco.UsersContent.ProfileActivity;
import com.taggroup.www.darzeeco.UsersContent.SharedPrefManager;
import com.taggroup.www.darzeeco.Utils.AboutUsActivity;
import com.taggroup.www.darzeeco.Utils.ContectUs;
import com.taggroup.www.darzeeco.Utils.SliderUtils;
import com.taggroup.www.darzeeco.Utils.ViewPagerAdpterMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView emailText, privacyText, termsText, serviceText, contectText, contectNo;
    private Fragment fragment = null;
    private MaterialSearchView searchView;
    private DrawerLayout drawer;


    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    RequestQueue rq;
    List<SliderUtils> sliderImg;
    ViewPagerAdpterMain viewPagerAdpter;

    String request_url = "http://192.168.2.41/darzee/slide.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView orderPlc = findViewById(R.id.orderbtn);
        orderPlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plcpage = new Intent(MainActivity.this, CategoryDesign.class);
                startActivity(plcpage);
            }
        });

        rq = Volley.newRequestQueue(this);

        sliderImg = new ArrayList<>();

        viewPager = findViewById(R.id.viewPagermain);
        sliderDotspanel = findViewById(R.id.slideDotsmain);

        sendRequest();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots_nonactive));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots_active));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 8000, 4000);


        fragment = new FragmentAll();
        CheckFrag();

        fragment = new FragmentComplete();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.screen_area1, fragment);


        fragment = new FragmentShirts();
        ft.replace(R.id.screen_area2, fragment);


        fragment = new FragmentTrousers();
        ft.replace(R.id.screen_area3, fragment);
        ft.commit();


        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolBar.setLogo(R.drawable.logo_footer);

        drawer = findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolBar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navgation_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView headname = navigationView.getHeaderView(0).findViewById(R.id.header_name);
        TextView heademail = navigationView.getHeaderView(0).findViewById(R.id.header_email);

        headname.setText(SharedPrefManager.getInstance(this).getUser().getLastname());
        heademail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        contectNo = findViewById(R.id.contectNo_text);
        emailText = findViewById(R.id.contect_mail);
        privacyText = findViewById(R.id.privacy_text);
        termsText = findViewById(R.id.term_text);
        TextView darzeetxt = findViewById(R.id.text1);
        final TextView bulkStitch = (TextView)findViewById(R.id.bulkStitchbtn);
        final EditText bName = (EditText)findViewById(R.id.bulkFullName);
        final EditText bemail = (EditText)findViewById(R.id.bulkEmail);
        final EditText bPhone = (EditText)findViewById(R.id.bulkPhone);
        final EditText bSubject = (EditText)findViewById(R.id.bulkSubject);
        final EditText bMessage = (EditText)findViewById(R.id.bulkMessage);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/splash.ttf");
        darzeetxt.setTypeface(typeface);


        privacyText = findViewById(R.id.privacy_text);
        privacyText.setMovementMethod(LinkMovementMethod.getInstance());

        contectText = findViewById(R.id.contect_text);
        contectText.setMovementMethod(LinkMovementMethod.getInstance());

        serviceText = findViewById(R.id.service_text);
        serviceText.setMovementMethod(LinkMovementMethod.getInstance());

        ImageView CheckMessage=(ImageView)findViewById(R.id.messageCheck);

        CheckMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        bulkStitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulkStitch.setFocusable(true);
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, "darzee@taggroup.co");
                email.putExtra(Intent.EXTRA_SUBJECT, bSubject.getText().toString().trim());
                email.putExtra(Intent.EXTRA_TEXT, bName.getText().toString().trim()+"\n"+
                        bMessage.getText().toString().trim()+"\n"+"My Contect Number: "+bPhone.getText().toString().trim()
                +"\n My Email: "+bemail.getText().toString().trim());
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose app to send mail"));
            }
        });

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
                AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.terms_dialog, null);
                myDialog.setView(myView);
                AlertDialog dialog = myDialog.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();
            }
        });


        SpannableString ss = new SpannableString(
                "  021-34322606-07"
        );
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 2, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new URLSpan("tel:0213432260607"), 2, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        contectNo.setText(ss);
        contectNo.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                SharedPrefManager.getInstance(this).logout();
                LetSelectSizePrefMngr.getInstance(this).ClearSize();
                LetSelectSizePrefMngr.getInstance(this).ClearSelectDupatta();
                LetSelectSizePrefMngr.getInstance(this).ClearSelectDaaman();
                LetSelectSizePrefMngr.getInstance(this).ClearSelectBackNeck();
                LetSelectSizePrefMngr.getInstance(this).ClearSelectFrontNeck();
                LetSelectSizePrefMngr.getInstance(this).ClearSelectShalwar();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginPage.class));
                break;
            case R.id.profile_menu:

                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;


            case R.id.action_favorite:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                return true;


        }


        return true;
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.activity_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.home) {

            fragment = new FragmentAll();
            CheckFrag();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.all_products) {

            fragment = new FragmentComplete();
            CheckFrag();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.gents_products) {

            fragment = new FragmentShirts();
            CheckFrag();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.ladies_products) {

            fragment = new FragmentTrousers();
            CheckFrag();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.favorite_activity) {

            startActivity(new Intent(getApplicationContext(), TrackActivity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.cart_activity) {

            startActivity(new Intent(getApplicationContext(), CartCustomActivity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.contect_us) {

            startActivity(new Intent(getApplicationContext(), ContectUs.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.about_us) {

            startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }


        drawer = findViewById(R.id.activity_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void CheckFrag() {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {


            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }

    public void sendRequest() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    SliderUtils sliderUtils = new SliderUtils();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        sliderUtils.setSliderImageUrl(jsonObject.getString("image"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                    }

                    sliderImg.add(sliderUtils);
                }


                viewPagerAdpter = new ViewPagerAdpterMain(sliderImg, MainActivity.this);

                viewPager.setAdapter(viewPagerAdpter);

                dotscount = viewPagerAdpter.getCount();
                dots = new ImageView[dotscount];

                for (int i = 0; i < dotscount; i++) {

                    dots[i] = new ImageView(MainActivity.this);
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots_nonactive));


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(8, 0, 8, 0);

                    sliderDotspanel.addView(dots[i], params);

                }

                dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dots_active));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();

            }
        });

        rq.add(jsonArrayRequest);

    }


    private void sendSMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Awesome");

            if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
            // any app that support this intent.
            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        }
        else // For early versions, do what worked for you before.
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address","03145024989");
            smsIntent.putExtra("sms_body","Awesome");
            startActivity(smsIntent);
        }
    }


}