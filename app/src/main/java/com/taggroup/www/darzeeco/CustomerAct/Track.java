package com.taggroup.www.darzeeco.CustomerAct;

/**
 * Created by muhammad.sufwan on 1/30/2018.
 */

public class Track {

    int id,user_id;
    Double amount;
    String Address, shipping_address,contact_number,status,invoice_date,pickUp_status,deliver_status;

    public Track(int id, int user_id, Double amount, String address, String shipping_address, String contact_number, String status, String invoice_date
    , String pickUp_status, String deliver_status) {
        this.id = id;
        this.user_id = user_id;
        this.amount = amount;
        this.Address = address;
        this.shipping_address = shipping_address;
        this.contact_number = contact_number;
        this.status = status;
        this.invoice_date = invoice_date;
        this.pickUp_status = pickUp_status;
        this.deliver_status = deliver_status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getAddress() {
        return Address;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getStatus() {
        return status;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public String getPickUp_status() {
        return pickUp_status;
    }

    public String getDeliver_status() {
        return deliver_status;
    }
}
