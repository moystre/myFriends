package com.easv.myfriends.myfriends.model;

import android.location.Location;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


public class Friend implements Serializable {

    //    <------------------- DECLARATIONS  ------------------->
    Integer  mId;
    String mName;
    String mAddress;
    Location mLocation;
    String mPhoneNumber;
    String mEmail;
    String mWebsite;
    Date mBirthday;
    String mPicturePath;

    //    <------------------- EMPTY CONSTRUCTOR ------------------->

    public Friend(){
        this.mName = "";
        this.mAddress = "";
        this.mPhoneNumber = "";
        this.mEmail = "";
        this.mWebsite = "";
        this.mBirthday = null;
        this.mPicturePath = "";
        this.mLocation = null;
    };

    //    <------------------- CONSTRUCTOR ------------------->

    public Friend(Integer mId, String mName, String mAddress, String mPhoneNumber, String mEmail, String mWebsite, Date mBirthday, String mPicturePath, Location mLocation) {
        this.mId = mId;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
        this.mWebsite = mWebsite;
        this.mBirthday = mBirthday;
        this.mPicturePath = mPicturePath;
        this.mLocation = mLocation;
    }

    //    <------------------- ID ------------------->

    public Integer getmId() {
        return mId;
    }

    //    <------------------- NAME ------------------->

    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }

    //    <------------------- ADDRESS ------------------->

    public String getmAddress() {
        return mAddress;
    }
    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    //    <------------------- LOCATION ------------------->

    public Location getmLocation() {
        return mLocation;
    }
    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    //    <------------------- PHONE ------------------->
    // TODO MANY PHONES
    public String getmPhoneNumber() {
        return mPhoneNumber;
    }
    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    //    <------------------- EMAIL ------------------->

    public String getmEmail() {
        return mEmail;
    }
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    //    <------------------- WEBSITE ------------------->

    public String getmWebsite() {
        return mWebsite;
    }
    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    //    <------------------- BIRTHDAY ------------------->

    public Date getmBirthday() {
        return mBirthday;
    }
    public String getmBirthdayStringDAYMONTH() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM");
        String strDt = simpleDate.format(getmBirthday());
        return strDt;
    }
    public void setmBirthday(Date mBirthday) {
        this.mBirthday = mBirthday;
    }

    //    <------------------- PICTURE ------------------->

    // TODO public File getmPicture() { return mPicture; } <- LOGIC FOR ACCESSING ACTUAL FILE OBJECT USING PATH
    public String getmPictureString() { return mPicturePath; }
    public void setmPicturePath(String mPicturePath) {
        this.mPicturePath = mPicturePath;
    }
}
