package com.easv.myfriends.myfriends;

import android.location.Location;
import java.io.File;
import java.util.Date;

/**
 * Created by User on 15-03-2018.
 */

public class FriendModel {

    String mName;
    String mAddress;
    Location mLocation;
    String mPhoneNumber;
    String mEmail;
    String mWebsite;
    Date mBirthday;
    File mPicture;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Location getmLocation() {
        return mLocation;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public Date getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(Date mBirthday) {
        this.mBirthday = mBirthday;
    }

    public File getmPicture() {
        return mPicture;
    }

    public void setmPicture(File mPicture) {
        this.mPicture = mPicture;
    }
}
