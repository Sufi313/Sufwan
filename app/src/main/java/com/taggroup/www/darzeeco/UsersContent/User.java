package com.taggroup.www.darzeeco.UsersContent;

/**
 * Created by muhammad.sufwan on 11/22/2017.
 */

public class User {


    private String firstname, lastname, email, phonenumber,address, gender, country, city, dateofbirth;
    private int id;
    public User( int id, String firstname, String lastname, String email, String phonenumber,
                 String address, String gender, String country, String city, String dateofbirth) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.dateofbirth = dateofbirth;

    }


    public int getId() {
        return id;
    }



    public String getFirstname() {
        return firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public String getEmail() {
        return email;
    }



    public String getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public class URLs {

        private static final String ROOT_URL = "http://192.168.2.41/darzee/userApi.php?apicall=";

        public static final String URL_REGISTER = ROOT_URL + "signup";
        public static final String URL_LOGIN= ROOT_URL + "login";

    }



}
