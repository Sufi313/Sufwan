package com.taggroup.www.darzeeco.UsersContent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taggroup.www.darzeeco.MainActivity;
import com.taggroup.www.darzeeco.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginPage extends AppCompatActivity {
  private TextView skipText,signUpText;
  private Button loginBtn;
  private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginBtn=(Button)findViewById(R.id.loginBtn);



        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }



            editTextUsername = (EditText) findViewById(R.id.userEmail);
            editTextPassword = (EditText) findViewById(R.id.passWord);


            //if user presses on login
            //calling the method login

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userLogin();
                }
            });


            //if user presses on not registered
            findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open register screen
                    finish();
                    startActivity(new Intent(getApplicationContext(), Registration.class));
                }
            });

        //if user presses on not registered
        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        }

        private void userLogin() {
            //first getting the values
            final String phonenumber = editTextUsername.getText().toString();
            final String password = editTextPassword.getText().toString();

            //validating inputs
            if (TextUtils.isEmpty(phonenumber)) {
                editTextUsername.setError("Please enter your phone number");
                editTextUsername.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Please enter your password");
                editTextPassword.requestFocus();
                return;
            }

            //if everything is fine

            class UserLogin extends AsyncTask<Void, Void, String> {

                ProgressBar progressBar;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    progressBar.setVisibility(View.GONE);


                    try {

                        //converting response to json object
                        JSONObject obj = new JSONObject(s);
//                        String error = obj.getString("error");


                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("users");

                            //creating a new user object
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("firstname"),
                                    userJson.getString("lastname"),
                                    userJson.getString("email"),
                                    userJson.getString("phonenumber"),
                                    userJson.getString("address"),
                                    userJson.getString("gender"),
                                    userJson.getString("country"),
                                    userJson.getString("city"),
                                    userJson.getString("dob")
                            );

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid phone Number or Password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("phonenumber", phonenumber);
                    params.put("password", password);
                    //returing the response
                    return requestHandler.sendPostRequest(User.URLs.URL_LOGIN, params);
                }
            }

            UserLogin ul = new UserLogin();
            ul.execute();
        }


    }

