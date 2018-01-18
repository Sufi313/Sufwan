package com.taggroup.www.darzeeco.CustomizeAndStanderd;

/*
 * Created by muhammad.sufwan on 1/9/2018.
 */

public class Sizes {

    private int id, user_id;
    private String fullname, sizeName;
    private float sShoulder, sChest, sWaist, sArmHole, sSleeveLength, sSleeve, sDaaman, sLength;
    private float tWaist, tHip, tThigh, tKnee, tLength, tOpening, tInseamLength;



    public Sizes(int id, int user_id, String sizeName, float sShoulder, float sChest, float sWaist, float sArmHole, float sSleeveLength, float sSleeve, float sDaaman, float sLength, float tWaist, float tHip, float tThigh, float tKnee, float tLength, float tOpening, float tInseamLength) {
        this.id = id;
        this.user_id = user_id;
        this.sizeName = sizeName;
        this.sShoulder = sShoulder;
        this.sChest = sChest;
        this.sWaist = sWaist;
        this.sArmHole = sArmHole;
        this.sSleeveLength = sSleeveLength;
        this.sSleeve = sSleeve;
        this.sDaaman = sDaaman;
        this.sLength = sLength;
        this.tWaist = tWaist;
        this.tHip = tHip;
        this.tThigh = tThigh;
        this.tKnee = tKnee;
        this.tLength = tLength;
        this.tOpening = tOpening;
        this.tInseamLength = tInseamLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public float getsShoulder() {
        return sShoulder;
    }

    public void setsShoulder(float sShoulder) {
        this.sShoulder = sShoulder;
    }

    public float getsChest() {
        return sChest;
    }

    public void setsChest(float sChest) {
        this.sChest = sChest;
    }

    public float getsWaist() {
        return sWaist;
    }

    public void setsWaist(float sWaist) {
        this.sWaist = sWaist;
    }

    public float getsArmHole() {
        return sArmHole;
    }

    public void setsArmHole(float sArmHole) {
        this.sArmHole = sArmHole;
    }

    public float getsSleeveLength() {
        return sSleeveLength;
    }

    public void setsSleeveLength(float sSleeveLength) {
        this.sSleeveLength = sSleeveLength;
    }

    public float getsSleeve() {
        return sSleeve;
    }

    public void setsSleeve(float sSleeve) {
        this.sSleeve = sSleeve;
    }

    public float getsDaaman() {
        return sDaaman;
    }

    public void setsDaaman(float sDaaman) {
        this.sDaaman = sDaaman;
    }

    public float getsLength() {
        return sLength;
    }

    public void setsLength(float sLength) {
        this.sLength = sLength;
    }

    public float gettWaist() {
        return tWaist;
    }

    public void settWaist(float tWaist) {
        this.tWaist = tWaist;
    }

    public float gettHip() {
        return tHip;
    }

    public void settHip(float tHip) {
        this.tHip = tHip;
    }

    public float gettThigh() {
        return tThigh;
    }

    public void settThigh(float tThigh) {
        this.tThigh = tThigh;
    }

    public float gettKnee() {
        return tKnee;
    }

    public void settKnee(float tKnee) {
        this.tKnee = tKnee;
    }

    public float gettLength() {
        return tLength;
    }

    public void settLength(float tLength) {
        this.tLength = tLength;
    }

    public float gettOpening() {
        return tOpening;
    }

    public void settOpening(float tOpening) {
        this.tOpening = tOpening;
    }

    public float gettInseamLength() {
        return tInseamLength;
    }

    public void settInseamLength(float tInseamLength) {
        this.tInseamLength = tInseamLength;
    }
}
