package com.taggroup.www.darzeeco.UsersContent;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.taggroup.www.darzeeco.MainActivity;
import com.taggroup.www.darzeeco.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;





public class Registration extends AppCompatActivity {


    EditText editTextfirstname,editTextlastname, editTextEmail, editTextPhonenumber,
            editTextPassword, editTextAddress, editTextDateOfBirth;
    Spinner gender,country,city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        gender= findViewById(R.id.spingender);
        country= findViewById(R.id.spincountry);
        city= findViewById(R.id.spincity);

        ArrayAdapter<CharSequence> genderAdapt = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapt);

        ArrayAdapter<CharSequence> countryAdapt = ArrayAdapter.createFromResource(this,
                R.array.country_array, android.R.layout.simple_spinner_item);
        countryAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countryAdapt);

        ArrayAdapter<CharSequence> cityAdapt = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapt);

        editTextAddress = findViewById(R.id.txtaddress);
        editTextfirstname = findViewById(R.id.firstname);
        editTextlastname = findViewById(R.id.txtlname);
        editTextEmail = findViewById(R.id.txtemail);
        editTextPhonenumber = findViewById(R.id.txtphone);
        editTextPassword = findViewById(R.id.txtpass);


        final Calendar myCalendar = Calendar.getInstance();
        editTextDateOfBirth = findViewById(R.id.txtdateofbirth);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MM-yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editTextDateOfBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Registration.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        findViewById(R.id.btnreg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

        findViewById(R.id.loginText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(Registration.this, LoginPage.class));
            }
        });

    }

    private void registerUser() {
        final String firstname = editTextfirstname.getText().toString().trim();
        final String lastname = editTextlastname.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String phonenumber = editTextPhonenumber.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String address = editTextAddress.getText().toString().trim();
        final String gendervalue = gender.getSelectedItem().toString().trim();
        final String countryvalue = country.getSelectedItem().toString().trim();
        final String cityvalue = city.getSelectedItem().toString().trim();
        final String dateofbirth = editTextDateOfBirth.getText().toString().trim();




        //first we will do the validations

        if (TextUtils.isEmpty(firstname)) {
            editTextfirstname.setError("Please enter First Name");
            editTextfirstname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(lastname)) {
            editTextlastname.setError("Please enter Last Name");
            editTextlastname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phonenumber)) {
            editTextPhonenumber.setError("Please enter Phone Number");
            editTextPhonenumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            editTextAddress.setError("Enter a Address");
            editTextAddress.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dateofbirth)) {
            editTextDateOfBirth.setError("Enter a Address");
            editTextDateOfBirth.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("phonenumber", phonenumber);
                params.put("password", password);
                params.put("address", address);
                params.put("gender", gendervalue);
                params.put("country", countryvalue);
                params.put("city", cityvalue);
                params.put("dob", dateofbirth);

                //returing the response
                return requestHandler.sendPostRequest(User.URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    boolean error = obj.getBoolean("error");


                    //if no error in response
                    if (error == false) {
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

                        //go to next Activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred "+obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }


}
